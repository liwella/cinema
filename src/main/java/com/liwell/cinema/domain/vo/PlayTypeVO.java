package com.liwell.cinema.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/31
 */
@Data
public class PlayTypeVO {

    private String playType;

    private String player;

    private List<PlayDetailVO> playDetailList;

}
