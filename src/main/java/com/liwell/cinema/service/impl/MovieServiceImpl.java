package com.liwell.cinema.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.MvPageDTO;
import com.liwell.cinema.domain.entity.Movie;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.MovieDetailVO;
import com.liwell.cinema.domain.vo.MvPageVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.CategoryMappingMapper;
import com.liwell.cinema.mapper.MovieMapper;
import com.liwell.cinema.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Service
@Slf4j
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    private final String MOVIE_ID = "movie_id";

    @Autowired
    private CategoryMappingMapper categoryMappingMapper;
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

    /**
     * 生成 movieId
     * @return
     */
    public int generateMovieId() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Long movieId = valueOperations.increment(MOVIE_ID);
        return movieId.intValue();
    }

}
