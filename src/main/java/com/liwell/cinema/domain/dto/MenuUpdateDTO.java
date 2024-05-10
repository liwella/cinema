package com.liwell.cinema.domain.dto;

import com.liwell.cinema.domain.enums.LayoutEnum;
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
public class MenuUpdateDTO {

    @NotNull(message = "菜单/权限id不能为空")
    private Integer id;

    @NotNull(message = "菜单/权限名称不能为空")
    private String name;

    private String code;

    private String path;

    private String icon;

    private LayoutEnum layout;

    private String component;

    private Boolean display;

    private Boolean enable;

    private Boolean keepAlive;

    @NotNull(message = "类型不能为空")
    private MenuTypeEnum type;

}
