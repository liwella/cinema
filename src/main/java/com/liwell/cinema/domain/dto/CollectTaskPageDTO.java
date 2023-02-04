package com.liwell.cinema.domain.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class CollectTaskPageDTO extends Page {

    private Date startTime;

    private Date endTime;

    private Integer sourceId;

    private CollectTaskStateEnum state;

}
