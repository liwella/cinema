package com.liwell.cinema.domain.entity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.liwell.cinema.domain.enums.MvAreaEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import com.liwell.cinema.domain.po.CollectDetail;
import com.liwell.cinema.util.EnumUtils;
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

    private Integer mvType;

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
        setMvType(null);
        setMvArea(EnumUtils.get(MvAreaEnum.class, collectDetail.getVod_area()));
        setMvYear(Integer.parseInt(collectDetail.getVod_year()));
        setCreateTime(DateUtil.parse(collectDetail.getVod_time(), DatePattern.NORM_DATETIME_PATTERN));
        setDescription(collectDetail.getVod_content());
        setState(StateEnum.VALID);
        setPicture(collectDetail.getVod_pic());
        setScore(Double.valueOf(collectDetail.getVod_douban_score()));
    }
}
