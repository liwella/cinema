package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.MvCollectDTO;
import com.liwell.cinema.domain.entity.Movie;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
public interface MovieService extends IService<Movie> {

    void collect(MvCollectDTO mvCollectDTO);

}
