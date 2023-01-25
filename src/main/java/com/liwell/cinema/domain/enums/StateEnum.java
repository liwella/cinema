package com.liwell.cinema.domain.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
public enum StateEnum implements IEnum<Integer> {

    VALID(1, "有效"),
    INVALID(2, "失效");

    private Integer value;

    private String description;

    StateEnum(Integer value, String description) {
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
