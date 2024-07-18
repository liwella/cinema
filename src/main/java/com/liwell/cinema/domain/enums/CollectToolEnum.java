package com.liwell.cinema.domain.enums;


import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/16
 */
@Getter
public enum CollectToolEnum implements BaseEnum {

    APPLE_CMS(1, "苹果cms"),
    SEA_CMS(2, "海洋cms"),
    FEIFEI_CMS(3, "飞飞cms"),
    ;

    private Integer value;

    private String description;

    CollectToolEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
