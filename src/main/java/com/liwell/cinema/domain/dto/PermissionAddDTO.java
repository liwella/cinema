package com.liwell.cinema.domain.dto;

import com.liwell.cinema.domain.enums.LayoutEnum;
import com.liwell.cinema.domain.enums.PermissionTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/27
 */
@Data
public class PermissionAddDTO {

    private Integer parentId;

    @NotNull(message = "类型不能为空")
    private PermissionTypeEnum type;

    @NotNull(message = "菜单名称不能为空")
    private String name;

    @NotNull(message = "编码不能为空")
    private String code;

    private String path;

    private String icon;

    private LayoutEnum layout;

    private String component;

    private Boolean display;

    private Boolean enable;

    private Boolean keepAlive;

    @NotNull(message = "序号不能为空")
    private Integer sort;

}
