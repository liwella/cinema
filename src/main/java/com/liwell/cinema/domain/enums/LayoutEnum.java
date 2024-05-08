package com.liwell.cinema.domain.enums;

import lombok.Getter;

@Getter
public enum LayoutEnum implements BaseEnum {

    BLANK(0, "empty", "空白"),
    SIMPLE(1, "simple", "简约"),
    NORMAL(2, "normal", "通用"),
    FULL(3, "full", "全面"),
    ;

    private Integer value;

    private String code;

    private String description;

    LayoutEnum(Integer value, String code, String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }
}
