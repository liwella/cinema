package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2024/5/17
 */
@Data
public class RoleUserDeleteDTO {

    @NotNull(message = "角色id不能为空")
    private Integer roleId;

    @NotNull(message = "用户id不能为空")
    private List<Integer> userIds;

}
