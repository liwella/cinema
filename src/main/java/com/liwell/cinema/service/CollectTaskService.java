package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.CollectTaskAddDTO;
import com.liwell.cinema.domain.dto.CollectTaskPageDTO;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.entity.CollectTask;
import com.liwell.cinema.domain.vo.CollectTaskPageVO;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/03
 */
public interface CollectTaskService extends IService<CollectTask> {

    Boolean addCollectTask(CollectTaskAddDTO dto);

    Boolean pauseCollectTask(Integer id);

    Boolean stopCollectTask(Integer id);

    Integer getTaskProcess(Integer id);

    Page<CollectTaskPageVO> pageCollectTask(CollectTaskPageDTO dto);

    Boolean startCollectTask(IdDTO dto);

}
