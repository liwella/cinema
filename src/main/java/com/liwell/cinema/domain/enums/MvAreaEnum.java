package com.liwell.cinema.domain.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
public enum MvAreaEnum implements IEnum<Integer> {

    UNKNOWN(-1, "未知"),
    CHINESE(1, "中国大陆");

    private Integer value;

    private String description;

    MvAreaEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
