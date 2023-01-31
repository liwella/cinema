package com.liwell.cinema.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/26
 */
@Data
public class Playlist {

    private Integer id;

    private Integer movieId;

    private Integer sourceId;

    private Integer sourceMovieId;

    private String playType;

    private String playUrl;

    private Date updateTime;

    private String separatorNote;

}
