package com.liwell.cinema.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * @author nxg产品线 litianyi
 * @company 深圳联软科技股份有限公司
 * @filename PlaylistAddPO.java
 * @purpose
 * @history 2023/8/25 litianyi
 */
@Data
public class PlaylistAddPO {

    private Integer id;

    private Integer movieId;

    private String mvName;

    private Integer sourceId;

    private Integer sourceMovieId;

    private String playType;

    private String playUrl;

    private Date updateTime;

    private String separatorNote;

}
