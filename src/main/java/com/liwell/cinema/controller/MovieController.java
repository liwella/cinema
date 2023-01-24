package com.liwell.cinema.controller;

import com.liwell.cinema.domain.dto.MvCollectDTO;
import com.liwell.cinema.service.MovieService;
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

    @PostMapping("/collect")
    public void collect(@RequestBody MvCollectDTO dto) {
        movieService.collect(dto);
    }

}
