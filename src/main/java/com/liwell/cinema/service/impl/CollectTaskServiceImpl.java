package com.liwell.cinema.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.CollectTaskAddDTO;
import com.liwell.cinema.domain.entity.CollectTask;
import com.liwell.cinema.domain.entity.Movie;
import com.liwell.cinema.domain.entity.Playlist;
import com.liwell.cinema.domain.entity.SourceConfig;
import com.liwell.cinema.domain.enums.CollectTaskStateEnum;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.CollectDetail;
import com.liwell.cinema.domain.po.CollectDetailResult;
import com.liwell.cinema.domain.po.CollectListResult;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.CollectTaskMapper;
import com.liwell.cinema.mapper.MovieMapper;
import com.liwell.cinema.mapper.PlaylistMapper;
import com.liwell.cinema.remote.SourceService;
import com.liwell.cinema.service.CategoryMappingService;
import com.liwell.cinema.service.CollectTaskService;
import com.liwell.cinema.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/03
 */
@Slf4j
@Service
public class CollectTaskServiceImpl extends ServiceImpl<CollectTaskMapper, CollectTask> implements CollectTaskService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private PlaylistMapper playlistMapper;
    @Autowired
    private CategoryMappingService categoryMappingService;
    @Autowired
    private SourceService sourceService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    @Transactional
    public Boolean addCollectTask(CollectTaskAddDTO dto) {
        if (CollectionUtil.isEmpty(dto.getSourceIds())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        List<CollectTask> collectTasks = baseMapper.selectList(
                new QueryWrapper<CollectTask>().in("source_id", dto.getSourceIds())
                        .notIn("state", CollectTaskStateEnum.STOP, CollectTaskStateEnum.FINISHED));
        if (CollectionUtil.isNotEmpty(collectTasks)) {
            throw new ResultException(ResultEnum.TASK_IN_EXECUTE);
        }
        List<CollectTask> collectTaskList = new ArrayList<>();
        for (Integer sourceId : dto.getSourceIds()) {
            CollectTask collectTask = new CollectTask();
            collectTask.init(sourceId, dto.getDuration());
            collectTaskList.add(collectTask);
        }
        baseMapper.addCollectTasks(collectTaskList);
        return true;
    }

//    @PostConstruct
    public void postConstruct() {
        taskExecutor.execute(new TaskScheduling());
    }

    /**
     * 采集任务调度
     */
    public class TaskScheduling implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    List<CollectTask> collectTaskList = baseMapper.selectList(
                            new QueryWrapper<CollectTask>().eq("state", 0));
                    for (CollectTask collectTask : collectTaskList) {
                        taskExecutor.execute(new CollectThread(collectTask));
                    }
                } catch (Exception e) {
                    log.error("采集任务调度出错：", e);
                }
            }
        }
    }

    /**
     * 采集任务执行
     */
    public class CollectThread implements Runnable {

        private CollectTask collectTask;

        public CollectThread(CollectTask collectTask) {
            this.collectTask = collectTask;
        }

        @Override
        public void run() {
            SourceConfig sourceConfig = sourceService.getSourceConfig(collectTask.getSourceId());
            CollectListResult baseInfoResult = sourceService.sourceBaseInfo(sourceConfig.getId());
            Integer pageCount = baseInfoResult.getPagecount();
            Map<Integer, Integer> categoryMapping = categoryMappingService.getCategoryMapping(sourceConfig.getId());
            for (int i = 1; i <= pageCount; i++) {
                CollectDetailResult detailResult = sourceService.pageSource(sourceConfig.getDetailUrl(), i);
                if (detailResult == null) {
                    continue;
                }
                List<CollectDetail> collectDetails = detailResult.getList();
                List<Movie> movies = new ArrayList<>();
                List<Playlist> playlists = new ArrayList<>();
                for (CollectDetail collectDetail : collectDetails) {
                    if (categoryMapping.get(collectDetail.getType_id()) == null) {
                        log.info("影片id：" + collectDetail.getVod_id() + "，片名：《" +
                                collectDetail.getVod_name() + "》，类型：" + collectDetail.getType_id()
                                + "-" + collectDetail.getType_name() + "，未匹配到对应类型，采集失败，跳过");
                        continue;
                    }
                    Movie movie = new Movie().init(collectDetail);
                    movie.setId(movieService.generateMovieId());
                    movie.setMvType(categoryMapping.get(collectDetail.getType_id()));
                    movies.add(movie);
                    playlists.add(generatePlaylist(collectDetail, movie.getId(), collectTask.getSourceId()));
                    log.info("影片id：" + collectDetail.getVod_id() + "，片名：《" +
                            collectDetail.getVod_name() + "》，类型：" + collectDetail.getType_id()
                            + "-" + collectDetail.getType_name() + "，采集成功！");
                }
                if (movies.size() == 0) {
                    continue;
                }
                movieMapper.insertMovies(movies);
                playlistMapper.insertPlaylist(playlists);
            }
        }
    }

    /**
     * 生成播放列表
     * @param collectDetail
     * @param movieId
     * @param sourceId
     * @return
     */
    private Playlist generatePlaylist(CollectDetail collectDetail, Integer movieId, Integer sourceId) {
        Playlist playlist = new Playlist();
        playlist.setMovieId(movieId);
        playlist.setSourceId(sourceId);
        playlist.setSourceMovieId(collectDetail.getVod_id());
        playlist.setPlayType(collectDetail.getVod_play_from());
        playlist.setPlayUrl(collectDetail.getVod_play_url());
        playlist.setSeparatorNote(collectDetail.getVod_play_note());
        playlist.setUpdateTime(DateUtil.parse(collectDetail.getVod_time(), DatePattern.NORM_DATETIME_PATTERN));
        return playlist;
    }

}
