package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/27
 */
@Data
public class RoleAddDTO {

    @NotNull(message = "角色名称不能为空")
    private String name;

    @NotNull(message = "角色编码不能为空")
    private String code;

    @NotNull(message = "角色状态不能为空")
    private Boolean enable;

    private List<Integer> permissionIds;

}
