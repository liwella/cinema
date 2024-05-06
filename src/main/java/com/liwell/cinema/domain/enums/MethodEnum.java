package com.liwell.cinema.domain.enums;

import lombok.Getter;

@Getter
public enum MethodEnum implements BaseEnum {

    GET(1, "GET"),
    POST(2, "POST"),
    PUT(3, "PUT"),
    DELETE(4, "DELETE");

    private Integer value;

    private String description;

    MethodEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
