package com.liwell.cinema.domain.entity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.liwell.cinema.domain.enums.MvAreaEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import com.liwell.cinema.domain.po.CollectDetail;
import com.liwell.cinema.util.EnumUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.regex.Pattern;

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

    private Integer updateInfo;

    private String description;

    private String actorList;

    private String directorList;

    private StateEnum state;

    private String picture;

    private String screenPicture;

    private Double score;

    public Movie init(CollectDetail collectDetail) {
        setMvName(collectDetail.getVod_name());
        setMvArea(MvAreaEnum.getByPattern(collectDetail.getVod_area().split(",")[0]));
        setMvYear(extractNumber(collectDetail.getVod_year()));
        try {
            setCreateTime(DateUtil.parse(collectDetail.getVod_year(), DatePattern.NORM_YEAR_PATTERN));
        } catch (Exception e) {
            setCreateTime(new Date());
        }
        setUpdateTime(new Date());
        setUpdateInfo(extractNumber(collectDetail.getVod_remarks()));
        setDescription(collectDetail.getVod_content());
        setActorList(collectDetail.getVod_actor());
        setDirectorList(collectDetail.getVod_director());
        setState(StateEnum.VALID);
        setPicture(collectDetail.getVod_pic());
        setScore(Double.valueOf(collectDetail.getVod_douban_score()));
        return this;
    }

    private Integer extractNumber(String target) {
        String REGEX = "[^0-9]";
        String result = Pattern.compile(REGEX).matcher(target).replaceAll("").trim();
        if (StringUtils.isNotBlank(result)) {
            return Integer.parseInt(result);
        }
        return null;
    }

}
