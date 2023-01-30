package com.liwell.cinema.domain.entity;

import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/30
 */
@Data
public class MovieClass {

    private Integer id;

    private String name;

    private Integer sort;

    private Integer parent;

    private Integer level;

}
