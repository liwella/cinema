package com.liwell.cinema.domain.vo;

import com.liwell.cinema.domain.enums.CollectToolEnum;
import com.liwell.cinema.domain.enums.SourceFormatEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import lombok.Data;

/**
 * @author litianyi
 * @filename SourceConfigListVO.java
 * @purpose
 * @history 2023/8/21 litianyi
 */
@Data
public class SourceConfigPageVO {

    private Integer id;

    private String sourceName;

    private String listUrl;

    private String detailUrl;

    private CollectToolEnum collectTool;

    private SourceFormatEnum sourceFormat;

    private String player;

    private StateEnum state;

}
