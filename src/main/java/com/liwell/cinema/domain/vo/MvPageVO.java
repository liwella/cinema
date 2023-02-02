package com.liwell.cinema.domain.vo;

import lombok.Data;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@Data
public class MvPageVO {

    private Integer movieId;

    private String mvName;

    private Integer updateInfo;

    private String picture;

    private String actorList;

    private Double score;

}
