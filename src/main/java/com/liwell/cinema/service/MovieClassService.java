package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.MovieClassAddDTO;
import com.liwell.cinema.domain.entity.MovieClass;
import com.liwell.cinema.domain.vo.MovieClassListVO;

import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
public interface MovieClassService extends IService<MovieClass> {

    List<MovieClassListVO> listMovieClass(Integer parent);

    Boolean addMovieClass(MovieClassAddDTO dto);

}
