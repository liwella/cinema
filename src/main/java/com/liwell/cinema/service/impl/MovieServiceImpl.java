package com.liwell.cinema.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.MvCollectDTO;
import com.liwell.cinema.domain.entity.CollectConfig;
import com.liwell.cinema.domain.entity.Movie;
import com.liwell.cinema.domain.entity.Playlist;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.CollectDetail;
import com.liwell.cinema.domain.po.CollectDetailResult;
import com.liwell.cinema.domain.po.CollectListResult;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.CollectConfigMapper;
import com.liwell.cinema.mapper.MovieMapper;
import com.liwell.cinema.mapper.PlaylistMapper;
import com.liwell.cinema.service.MovieService;
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
                Movie movie = new Movie();
                movie.init(collectDetail);
                int movieId = generateMovieId();
                movie.setId(movieId);
                movies.add(movie);
                playlists.addAll(generatePlaylist(collectDetail, movieId));
            }
            baseMapper.insertMovies(movies);
            playlistMapper.insertPlaylist(playlists);
        }
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

    private int generateMovieId() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Long movieId = valueOperations.increment(MOVIE_ID);
        return movieId.intValue();
    }

}
