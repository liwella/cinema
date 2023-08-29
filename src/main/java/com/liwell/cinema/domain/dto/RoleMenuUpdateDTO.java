package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author nxg产品线 litianyi
 * @company 深圳联软科技股份有限公司
 * @filename RoleMenuUpdateDTO.java
 * @purpose
 * @history 2023/8/29 litianyi
 */
@Data
public class RoleMenuUpdateDTO {

    @NotNull(message = "角色id不能为空")
    private Integer roleId;

    private List<Integer> menuIds;

}
