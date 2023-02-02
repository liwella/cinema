package com.liwell.cinema.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/02
 */
@Data
public class CategoryUpdateDTO {

    @NotNull(message = "分类id不能为空")
    private Integer id;

    @Length(min = 1, message = "分类名称长度不能为0")
    private String name;

}
