package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/27
 */
@Data
public class RoleUpdateDTO {

    @NotNull(message = "角色id不能为空")
    private Integer id;

    @NotNull(message = "角色名称不能为空")
    private String roleName;

}
