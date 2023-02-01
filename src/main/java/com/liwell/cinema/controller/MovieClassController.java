package com.liwell.cinema.controller;

import com.liwell.cinema.domain.dto.MovieClassAddDTO;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.MovieClassListVO;
import com.liwell.cinema.service.MovieClassService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@RestController
@RequestMapping("/movieClass")
public class MovieClassController {

    @Autowired
    private MovieClassService movieClassService;

    @PostMapping("/listMovieClass")
    public Result<List<MovieClassListVO>> listMovieClass() {
        return ResultUtil.success(movieClassService.listMovieClass(null));
    }

    @PostMapping("/addMovieClass")
    public Result addMovieClass(@RequestBody @Valid MovieClassAddDTO dto) {
        return ResultUtil.trueOrFalse(movieClassService.addMovieClass(dto));
    }

}
