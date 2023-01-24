package com.liwell.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.MvCollectDTO;
import com.liwell.cinema.domain.entity.Movie;
import com.liwell.cinema.mapper.MovieMapper;
import com.liwell.cinema.service.MovieService;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Override
    public void collect(MvCollectDTO mvCollectDTO) {

    }

}
