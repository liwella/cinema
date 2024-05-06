package com.liwell.cinema.domain.enums;

import lombok.Getter;

@Getter
public enum LayoutEnum implements BaseEnum {

    BLANK(0, "空白"),
    SIMPLE(1, "简约"),
    NORMAL(2, "通用"),
    FULL(3, "全面")
    ;

    private Integer value;

    private String description;

    LayoutEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

}
