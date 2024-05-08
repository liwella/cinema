package com.liwell.cinema.domain.enums;

import lombok.Getter;

@Getter
public enum MenuDisplayEnum implements BaseEnum {

    DISPLAY(1, "展示"),
    HIDE(0, "隐藏"),
    ;

    private Integer value;

    private String description;

    MenuDisplayEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

}
