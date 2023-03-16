package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/16
 */
@Getter
public enum RoleEnum implements BaseEnum {

    ADMIN(0, "管理员"),
    TRAVELER(1, "游客"),
    NORMAL(2, "普通用户"),
    VIP(3, "vip用户")
    ;

    private int value;

    private String description;

    RoleEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

}
