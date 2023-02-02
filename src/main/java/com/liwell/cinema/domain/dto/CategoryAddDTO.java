package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@Data
public class CategoryAddDTO {

    @NotNull(message = "分类名称不能为空")
    private String name;

    private Integer parent;

}
