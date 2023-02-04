package com.liwell.cinema.domain.vo;

import com.liwell.cinema.domain.enums.CollectTaskStateEnum;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/2/4
 */
@Data
public class CollectTaskPageVO {

    private Integer id;

    private Integer sourceId;

    private Integer sourceName;

    private Integer duration;

    private Date createTime;

    private Date startTime;

    private Date pauseTime;

    private Date stopTime;

    private Integer process;

    private CollectTaskStateEnum state;

    private Date finishTime;

}
