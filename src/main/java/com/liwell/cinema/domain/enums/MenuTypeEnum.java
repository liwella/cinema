package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
@Getter
public enum MenuTypeEnum implements BaseEnum {

    CATEGORY(0, "目录"),
    MENU(1, "菜单"),
    PERMISSION(2, "权限")
    ;

    private Integer value;

    private String description;

    MenuTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

}
