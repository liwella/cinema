package com.liwell.cinema.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liwell.cinema.domain.enums.MvAreaEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@Data
public class MvPageVO {

    private Integer movieId;

    private String mvName;

    private Integer categoryId;

    private String categoryName;

    private MvAreaEnum mvArea;

    private String mvYear;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateDate;

    private Integer updateInfo;

    private String description;

    private String actorList;

    private String directorList;

    private StateEnum state;

    private String picture;

    private Double score;

}
