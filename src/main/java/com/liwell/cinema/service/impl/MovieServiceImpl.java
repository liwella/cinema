package com.liwell.cinema.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.MvAddDTO;
import com.liwell.cinema.domain.dto.MvPageDTO;
import com.liwell.cinema.domain.entity.Movie;
import com.liwell.cinema.domain.entity.Playlist;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.MovieDetailVO;
import com.liwell.cinema.domain.vo.MvPageVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.CategoryMappingMapper;
import com.liwell.cinema.mapper.MovieMapper;
import com.liwell.cinema.mapper.PlaylistMapper;
import com.liwell.cinema.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Service
@Slf4j
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    private final String MOVIE_ID = "movie:id:";
    private final String MOVIE_EXIST_ID = "movie:existsId:";

    @Autowired
    private CategoryMappingMapper categoryMappingMapper;
    @Autowired
    private PlaylistMapper playlistMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 分页列表
     * @param mvPageDTO
     * @return
     */
    @Override
    public Page<MvPageVO> pageMovie(MvPageDTO mvPageDTO) {
        return baseMapper.pageMovie(mvPageDTO);
    }

    /**
     * 获取影片详情
     * @param dto
     * @return
     */
    @Override
    public MovieDetailVO getMovieDetail(IdDTO dto) {
        MovieDetailVO detailVO = baseMapper.getMovieDetail(dto);
        if (detailVO == null) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        return detailVO;
    }

    @Override
    public Boolean addOrUpdate(MvAddDTO dto) {
        if (Objects.isNull(dto.getMovieId())) {
            int movieId = generateMovieId(null);
            dto.setMovieId(movieId);
        }
        baseMapper.addOrUpdate(dto);
        return true;
    }

    @Override
    public Boolean deleteMovie(IdDTO dto) {
        if (Objects.nonNull(dto.getId())) {
            Movie movie = baseMapper.selectById(dto.getId());
            if (Objects.nonNull(movie)) {
                redisTemplate.delete(MOVIE_EXIST_ID + movie.getMvName());
            }
            baseMapper.deleteById(dto.getId());
            playlistMapper.delete(new QueryWrapper<Playlist>().eq("movie_id", dto.getId()));
            return true;
        }
        if (Objects.nonNull(dto.getIds())) {
            List<Movie> movies = baseMapper.selectList(new QueryWrapper<Movie>().in("id", dto.getIds()));
            if (CollectionUtil.isNotEmpty(movies)) {
                for (Movie movie : movies) {
                    redisTemplate.delete(MOVIE_EXIST_ID + movie.getMvName());
                }
            }
            baseMapper.delete(new QueryWrapper<Movie>().in("id", dto.getIds()));
            playlistMapper.delete(new QueryWrapper<Playlist>().in("movie_id", dto.getIds()));
            return true;
        }
        throw new ResultException(ResultEnum.PARAMETER_ERROR);
    }

    /**
     * 根据影片名称获取 movieId
     * @return
     */
    public int generateMovieId(String mvName) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        if (Objects.nonNull(mvName)) {
            Object id = valueOperations.get(MOVIE_EXIST_ID + mvName);
            if (Objects.nonNull(id)) {
                return (int) id;
            }
        }
        Long movieId = valueOperations.increment(MOVIE_ID);
        valueOperations.set(MOVIE_EXIST_ID + mvName, movieId, 10, TimeUnit.DAYS);
        return movieId.intValue();
    }

    @Override
    public List<Integer> listNonSourceMovie() {
        return baseMapper.listNonSourceMovie();
    }
}
