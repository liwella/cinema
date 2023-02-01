package com.liwell.cinema.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.MvCollectDTO;
import com.liwell.cinema.domain.dto.MvPageDTO;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.MvPageVO;
import com.liwell.cinema.service.MovieService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @PostMapping("/collect")
    public Result collect(@RequestBody @Valid MvCollectDTO dto) {
        movieService.collect(dto);
        return ResultUtil.success();
    }

    @PostMapping("/pageMovie")
    public Result<Page<MvPageVO>> pageMovie(@RequestBody MvPageDTO mvPageDTO) {
        return ResultUtil.success(movieService.pageMovie(mvPageDTO));
    }

}