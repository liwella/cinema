package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2024/6/13
 */
@Getter
public enum SourceFormatEnum implements BaseEnum {

    JSON(1, "json"),
    XML(2, "xml"),
    ;

    private Integer value;

    private String description;

    SourceFormatEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
