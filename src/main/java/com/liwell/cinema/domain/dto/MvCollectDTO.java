package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Data
public class MvCollectDTO {

    private Integer time;

    @NotNull
    private Integer sourceId;

}
