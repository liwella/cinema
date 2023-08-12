package com.liwell.cinema.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liwell.cinema.domain.enums.MvAreaEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author nxg产品线 litianyi
 * @company 深圳联软科技股份有限公司
 * @filename MvAddDTO.java
 * @purpose
 * @history 2023/8/11 litianyi
 */
@Data
public class MvAddDTO {

    private Integer movieId;

    @NotNull(message = "影片名称不能为空")
    private String mvName;

    @NotNull(message = "影片类型不能为空")
    private Integer mvType;

    private MvAreaEnum mvArea;

    private Integer mvYear;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @NotNull(message = "影片更新集数不能为空")
    private Integer updateInfo;

    private String description;

    private String actorList;

    private String directorList;

    private StateEnum state;

    private String picture;

    private String screenPicture;

    private Double score;

}
