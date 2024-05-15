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
public enum PermissionTypeEnum implements BaseEnum {

    CATEGORY(0, "目录"),
    MENU(1, "菜单"),
    PERMISSION(2, "按钮")
    ;

    private Integer value;

    private String description;

    PermissionTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public void validate(PermissionTypeEnum parentType) {
        if ((this == PermissionTypeEnum.CATEGORY || this == PermissionTypeEnum.MENU)
                && (parentType != null && parentType != PermissionTypeEnum.CATEGORY)) {
            throw new ResultException(ResultEnum.MENU_CAN_CATEGORY_OR_TOP);
        }
        if (this == PermissionTypeEnum.PERMISSION && parentType != PermissionTypeEnum.MENU) {
            throw new ResultException(ResultEnum.PERMISSION_ONLY_MENU);
        }
    }
}
