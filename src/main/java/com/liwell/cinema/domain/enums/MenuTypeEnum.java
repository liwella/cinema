package com.liwell.cinema.domain.enums;

import com.liwell.cinema.exception.ResultException;
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
    PERMISSION(2, "按钮")
    ;

    private Integer value;

    private String description;

    MenuTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public void validate(MenuTypeEnum parentType) {
        if ((this == MenuTypeEnum.CATEGORY || this == MenuTypeEnum.MENU)
                && (parentType != null && parentType != MenuTypeEnum.CATEGORY)) {
            throw new ResultException(ResultEnum.MENU_CAN_CATEGORY_OR_TOP);
        }
        if (this == MenuTypeEnum.PERMISSION && parentType != MenuTypeEnum.MENU) {
            throw new ResultException(ResultEnum.PERMISSION_ONLY_MENU);
        }
    }
}
