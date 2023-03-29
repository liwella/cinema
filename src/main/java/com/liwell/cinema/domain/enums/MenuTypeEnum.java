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

    MENU(1, "菜单"),
    CATEGORY(2, "目录"),
    PERMISSION(3, "权限")
    ;

    private Integer value;

    private String description;

    MenuTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

}
