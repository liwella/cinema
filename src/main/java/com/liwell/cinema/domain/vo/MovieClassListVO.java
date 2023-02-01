package com.liwell.cinema.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@Data
public class MovieClassListVO {

    private Integer id;

    private String name;

    private Integer sort;

    private Integer level;

    private List<MovieClassListVO> children;

}
