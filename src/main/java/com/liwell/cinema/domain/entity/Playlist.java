package com.liwell.cinema.domain.entity;

import lombok.Data;

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

    private String source;

    private String tag;

    private String url;

}
