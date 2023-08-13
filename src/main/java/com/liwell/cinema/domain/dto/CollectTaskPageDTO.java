package com.liwell.cinema.domain.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.enums.CollectTaskStateEnum;
import com.liwell.cinema.domain.po.TimeRange;
import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/2/4
 */
@Data
public class CollectTaskPageDTO extends Page {

    private TimeRange startTime;

    private TimeRange endTime;

    private Integer sourceId;

    private CollectTaskStateEnum state;

}
