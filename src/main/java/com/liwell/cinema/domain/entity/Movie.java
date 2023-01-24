package com.liwell.cinema.domain.entity;

import com.liwell.cinema.domain.enums.MvAreaEnum;
import com.liwell.cinema.domain.enums.MvStateEnum;
import com.liwell.cinema.domain.enums.MvTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Data
public class Movie {

    private Integer id;

    private String mvName;

    private MvTypeEnum mvType;

    private MvAreaEnum mvArea;

    private Integer mvYear;

    private Date createTime;

    private Date updateTime;

    private String description;

    private MvStateEnum state;

    private String picture;

    private String screenPicture;

    private Double score;

}
