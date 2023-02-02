package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/02
 */
@Data
public class CategoryMappingUpdateDTO {

    @NotNull
    private Integer sourceId;

    @NotNull
    private Integer sourceTypeId;

    @NotNull
    private Integer categoryId;

}
