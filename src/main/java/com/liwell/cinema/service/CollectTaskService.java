package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.CollectTaskAddDTO;
import com.liwell.cinema.domain.entity.CollectTask;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/03
 */
public interface CollectTaskService extends IService<CollectTask> {

    Boolean addCollectTask(CollectTaskAddDTO dto);

}
