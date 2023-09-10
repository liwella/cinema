package com.liwell.cinema.domain.dto;

import lombok.Data;

/**
 * @author litianyi
 * @filename SourceConfigAddDTO.java
 * @purpose
 * @history 2023/8/21 litianyi
 */
@Data
public class SourceConfigAddDTO {

    private String sourceName;

    private String listUrl;

    private String detailUrl;

    private String player;

}
