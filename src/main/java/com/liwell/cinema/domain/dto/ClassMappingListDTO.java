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
public class ClassMappingListDTO {

    @NotNull(message = "采集源id不能为空")
    private Integer sourceId;

}
