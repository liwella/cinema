package com.liwell.cinema.domain.vo;

import com.liwell.cinema.domain.enums.StateEnum;
import lombok.Data;

/**
 * @author litianyi
 * @filename SourceConfigListVO.java
 * @purpose
 * @history 2023/8/21 litianyi
 */
@Data
public class SourceConfigListVO {

    private Integer id;

    private String sourceName;

    private String listUrl;

    private String detailUrl;

    private StateEnum state;

}
