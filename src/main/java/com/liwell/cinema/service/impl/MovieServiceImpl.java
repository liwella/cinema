package com.liwell.cinema.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.MvCollectDTO;
import com.liwell.cinema.domain.entity.ClassMapping;
import com.liwell.cinema.domain.entity.CollectConfig;
import com.liwell.cinema.domain.entity.Movie;
import com.liwell.cinema.domain.entity.Playlist;
import com.liwell.cinema.domain.enums.MvAreaEnum;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import com.liwell.cinema.domain.po.CollectDetail;
import com.liwell.cinema.domain.po.CollectDetailResult;
import com.liwell.cinema.domain.po.CollectListResult;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.ClassMappingMapper;
import com.liwell.cinema.mapper.CollectConfigMapper;
import com.liwell.cinema.mapper.MovieMapper;
import com.liwell.cinema.mapper.PlaylistMapper;
import com.liwell.cinema.service.MovieService;
import com.liwell.cinema.util.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    private final String MOVIE_ID = "movie_id";

    @Autowired
    private CollectConfigMapper collectConfigMapper;
    @Autowired
    private PlaylistMapper playlistMapper;
    @Autowired
    private ClassMappingMapper classMappingMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional
    public void collect(MvCollectDTO mvCollectDTO) {
        CollectConfig collectConfig = collectConfigMapper.selectOne(
                new QueryWrapper<CollectConfig>().eq("id", mvCollectDTO.getCollectId()).eq("state", 1));
        if (collectConfig == null) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        String listUrl = collectConfig.getListUrl();
        ResponseEntity<CollectListResult> pageCountResponse = restTemplate
                .getForEntity(listUrl, CollectListResult.class, new HashMap<>());
        if (pageCountResponse.getBody() == null || pageCountResponse.getStatusCodeValue() != 200) {
            throw new ResultException(ResultEnum.THIRD_INTERFACE_ERROR);
        }
        Integer pagecount = pageCountResponse.getBody().getPagecount();
        Map<Integer, Integer> classMapping = getClassMapping(mvCollectDTO.getCollectId());
        for (int i = 0; i < pagecount; i++) {
            Map<String, Integer> listParam = new HashMap<>();
            listParam.put("pg", i);
            ResponseEntity<CollectListResult> responseEntity = restTemplate.getForEntity(listUrl, CollectListResult.class, listParam);
            if (responseEntity.getBody() == null) {
                continue;
            }
            List<CollectDetail> list = responseEntity.getBody().getList();
            List<Integer> idList = list.stream().map(CollectDetail::getVod_id).collect(Collectors.toList());
            String detailUrl = collectConfig.getDetailUrl();
            Map<String, List<Integer>> detailParam = new HashMap<>();
            detailParam.put("ids", idList);
            ResponseEntity<CollectDetailResult> detailResultResponseEntity = restTemplate.getForEntity(detailUrl, CollectDetailResult.class, detailParam);
            CollectDetailResult detailResult = detailResultResponseEntity.getBody();
            if (detailResult == null) {
                continue;
            }
            List<CollectDetail> collectDetails = detailResult.getList();
            List<Movie> movies = new ArrayList<>();
            List<Playlist> playlists = new ArrayList<>();
            for (CollectDetail collectDetail : collectDetails) {
                Movie movie = generateMovie(collectDetail, classMapping);
                movies.add(movie);
                playlists.addAll(generatePlaylist(collectDetail, movie.getId()));
            }
            baseMapper.insertMovies(movies);
            playlistMapper.insertPlaylist(playlists);
        }
    }

    private Map<Integer, Integer> getClassMapping(Integer collectId) {
        List<ClassMapping> classMappingList = classMappingMapper.selectList(new QueryWrapper<ClassMapping>().eq("source_id", collectId));
        Map<Integer, Integer> result = new HashMap<>();
        for (ClassMapping classMapping : classMappingList) {
            result.put(classMapping.getSourceTypeId(), classMapping.getMovieClassId());
        }
        return result;
    }

    private Movie generateMovie(CollectDetail collectDetail, Map<Integer, Integer> classMapping) {
        Movie movie = new Movie();
        movie.setMvName(collectDetail.getVod_name());
        movie.setMvType(classMapping.get(collectDetail.getType_id()));
        movie.setMvArea(EnumUtils.get(MvAreaEnum.class, collectDetail.getVod_area()));
        movie.setMvYear(Integer.parseInt(collectDetail.getVod_year()));
        movie.setCreateTime(DateUtil.parse(collectDetail.getVod_time(), DatePattern.NORM_DATETIME_PATTERN));
        movie.setDescription(collectDetail.getVod_content());
        movie.setState(StateEnum.VALID);
        movie.setPicture(collectDetail.getVod_pic());
        movie.setScore(Double.valueOf(collectDetail.getVod_douban_score()));
        int movieId = generateMovieId();
        movie.setId(movieId);
        return movie;
    }

    private int generateMovieId() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Long movieId = valueOperations.increment(MOVIE_ID);
        return movieId.intValue();
    }

    private List<Playlist> generatePlaylist(CollectDetail collectDetail, Integer movieId) {
        List<Playlist> result = new ArrayList<>();
        String[] playSource = collectDetail.getVod_play_from().split("\\$\\$\\$");
        String[] sourceUrls = collectDetail.getVod_play_url().split("\\$\\$\\$");
        for (int i = 0; i < sourceUrls.length; i++) {
            String sourceUrl = sourceUrls[i];
            String[] tagAndUrls = sourceUrl.split("#");
            for (String tagAndUrl : tagAndUrls) {
                String[] tagUrl = tagAndUrl.split("\\$");
                Playlist playlist = new Playlist();
                playlist.setMovieId(movieId);
                playlist.setSource(playSource[i]);
                playlist.setTag(tagUrl[0]);
                playlist.setUrl(tagUrl[1]);
                result.add(playlist);
            }
        }
        return result;
    }

}
