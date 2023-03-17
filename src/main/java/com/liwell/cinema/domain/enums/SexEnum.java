package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/16
 */
@Getter
public enum SexEnum implements BaseEnum {

    WOMAN(0, "女"),
    MAN(1, "男")
    ;

    private Integer value;

    private String description;

    SexEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

}
