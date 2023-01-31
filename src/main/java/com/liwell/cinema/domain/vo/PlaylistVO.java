package com.liwell.cinema.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/01/31
 */
@Data
public class PlaylistVO {

    private Integer movieId;

    private String sourceName;

    private String playType;

    private List<PlayDetailVO> playDetailList;

}
