package com.liwell.cinema.domain.entity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.liwell.cinema.domain.enums.MvAreaEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import com.liwell.cinema.domain.po.CollectDetail;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Slf4j
@Data
public class Movie {

    private Integer id;

    private String mvName;

    private Integer mvType;

    private MvAreaEnum mvArea;

    private Integer mvYear;

    private Date createDate;

    private Date updateDate;

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
        long createDateMillis = Objects.isNull(collectDetail.getVod_time_add()) ?
                System.currentTimeMillis() : collectDetail.getVod_time_add() * 1000;
        setMvYear(DateUtil.year(DateUtil.date(createDateMillis)));
        setCreateDate(DateUtil.date(createDateMillis));
        setUpdateDate(StringUtils.isBlank(collectDetail.getVod_time()) ? new Date() :
                DateUtil.parse(collectDetail.getVod_time().substring(0,
                        collectDetail.getVod_time().lastIndexOf(" ")), DatePattern.NORM_DATE_PATTERN));
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
        try {
            if (StringUtils.isNotBlank(result)) {
                return Integer.parseInt(result);
            }
        } catch (Exception e) {
            log.error("上映日期转换出错：", e);
        }
        return null;
    }

}
