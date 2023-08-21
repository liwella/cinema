package com.liwell.cinema.domain.dto;

import com.liwell.cinema.domain.enums.StateEnum;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author litianyi
 * @filename SourceConfigUpdateDTO.java
 * @purpose
 * @history 2023/8/21 litianyi
 */
@Data
public class SourceConfigUpdateDTO {

    @NotNull(message = "采集源id不能为空")
    private Integer id;

    private String sourceName;

    @NotNull(message = "采集列表url不能为空")
    private String listUrl;

    @NotNull(message = "采集详情url不能为空")
    private String DetailUrl;

    @NotNull(message = "状态不能为空")
    private StateEnum state;

}
