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
public class RoleUpdateDTO {

    @NotNull(message = "角色id不能为空")
    private Integer id;

    private String name;

    private Boolean enable;

    private List<Integer> permissionIds;

}
