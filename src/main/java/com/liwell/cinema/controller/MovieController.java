package com.liwell.cinema.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.MvPageDTO;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.MovieDetailVO;
import com.liwell.cinema.domain.vo.MvPageVO;
import com.liwell.cinema.service.MovieService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/pageMovie")
    public Result<Page<MvPageVO>> pageMovie(@RequestBody MvPageDTO mvPageDTO) {
        return ResultUtil.success(movieService.pageMovie(mvPageDTO));
    }

    @PostMapping("/detail")
    public Result<MovieDetailVO> getMovieDetail(@RequestBody IdDTO dto) {
        return ResultUtil.success(movieService.getMovieDetail(dto));
    }

}