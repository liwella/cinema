package com.liwell.cinema.domain.entity;

import com.liwell.cinema.domain.enums.MvAreaEnum;
import com.liwell.cinema.domain.enums.MvTypeEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import com.liwell.cinema.domain.po.CollectDetail;
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

    private StateEnum state;

    private String picture;

    private String screenPicture;

    private Double score;

    public void init(CollectDetail collectDetail) {
        setMvName(collectDetail.getVod_name());

    }
}
