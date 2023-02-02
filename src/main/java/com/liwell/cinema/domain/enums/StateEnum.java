package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Getter
public enum StateEnum implements BaseEnum {

    VALID(1, "有效"),
    INVALID(2, "失效");

    private Integer value;

    private String description;

    StateEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

}
