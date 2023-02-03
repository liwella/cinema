package com.liwell.cinema.domain.entity;

import com.liwell.cinema.domain.enums.CollectTaskStateEnum;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/03
 */
@Data
public class CollectTask {

    private Integer id;

    private Integer sourceId;

    private Integer duration;

    private Date createTime;

    private Date startTime;

    private Date suspendTime;

    private Integer currentPage;

    private Integer totalPage;

    private CollectTaskStateEnum state;

    private Date finishTime;

    public void init(Integer sourceId, Integer duration) {
        setSourceId(sourceId);
        setDuration(duration);
        setCreateTime(new Date());
    }

}