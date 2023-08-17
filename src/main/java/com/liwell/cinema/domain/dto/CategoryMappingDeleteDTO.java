package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author litianyi
 * @filename CategoryMappingDeleteDTO.java
 * @purpose
 * @history 2023/8/17 litianyi
 */
@Data
public class CategoryMappingDeleteDTO {

    @NotNull(message = "采集源id不能为空")
    private Integer sourceId;

    @NotNull(message = "采集分类id不能为空")
    private Integer sourceTypeId;

}
