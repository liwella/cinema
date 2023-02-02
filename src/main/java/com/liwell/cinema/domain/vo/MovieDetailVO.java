package com.liwell.cinema.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liwell.cinema.domain.enums.MvAreaEnum;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/02
 */
@Data
public class MovieDetailVO {

    private Integer id;

    private String mvName;

    private Integer mvType;

    private String mvTypeName;

    private MvAreaEnum mvArea;

    private Integer mvYear;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer updateInfo;

    private String description;

    private String actorList;

    private String directorList;

    private String picture;

    private Double score;

}
