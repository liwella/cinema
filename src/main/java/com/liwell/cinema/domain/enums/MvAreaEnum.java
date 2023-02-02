package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Getter
public enum MvAreaEnum implements BaseEnum {

    UNKNOWN(-1, "未知"),
    CHINESE(1, "中国大陆"),
    HKM(2, "港澳"),
    TAIWAN(3, "台湾");

    private Integer value;

    private String description;

    MvAreaEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

}