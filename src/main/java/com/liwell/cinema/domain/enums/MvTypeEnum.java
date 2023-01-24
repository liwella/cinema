package com.liwell.cinema.domain.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
public enum MvTypeEnum implements IEnum<Integer> {

    ACTION(1, "动作");

    private Integer value;

    private String description;

    MvTypeEnum(Integer value, String description) {
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