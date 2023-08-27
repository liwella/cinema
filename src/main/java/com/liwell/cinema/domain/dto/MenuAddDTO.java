package com.liwell.cinema.domain.dto;

import com.liwell.cinema.domain.enums.MenuTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/27
 */
@Data
public class MenuAddDTO {

    @NotNull(message = "菜单/权限名称不能为空")
    private String menuName;

    private String url;

    private String permission;

    @NotNull(message = "类型不能为空")
    private MenuTypeEnum type;

    private String icon;

    private Integer parentId;

}
