package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.MvAddDTO;
import com.liwell.cinema.domain.dto.MvPageDTO;
import com.liwell.cinema.domain.entity.Movie;
import com.liwell.cinema.domain.vo.MovieDetailVO;
import com.liwell.cinema.domain.vo.MvPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Mapper
@Repository
public interface MovieMapper extends BaseMapper<Movie> {

    void insertMovies(List<Movie> movies);

    Page<MvPageVO> pageMovie(MvPageDTO mvPageDTO);

    MovieDetailVO getMovieDetail(IdDTO dto);

    void addOrUpdate(MvAddDTO dto);

    void deleteNonSourceMovie();

}
