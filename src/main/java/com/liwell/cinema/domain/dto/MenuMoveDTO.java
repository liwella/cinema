package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/9/2
 */
@Data
public class MenuMoveDTO {

    @NotNull(message = "菜单id不能为空")
    private Integer id;

    @NotNull(message = "移动方向不能为空")
    private Boolean up;

}
