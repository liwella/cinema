package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/16
 */
@Getter
public enum DeleteEnum implements BaseEnum {

    UN_DELETED(0, "未删除"),
    DELETED(1, "已删除");

    private Integer value;

    private String description;

    DeleteEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
