package com.liwell.cinema.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.CollectTaskAddDTO;
import com.liwell.cinema.domain.entity.CollectTask;
import com.liwell.cinema.domain.enums.CollectTaskStateEnum;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.CollectTaskMapper;
import com.liwell.cinema.service.CollectTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/03
 */
@Service
public class CollectTaskServiceImpl extends ServiceImpl<CollectTaskMapper, CollectTask> implements CollectTaskService {

    @Override
    @Transactional
    public Boolean addCollectTask(CollectTaskAddDTO dto) {
        if (CollectionUtil.isEmpty(dto.getSourceIds())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        List<CollectTask> collectTasks = baseMapper.selectList(
                new QueryWrapper<CollectTask>().in("source_id", dto.getSourceIds())
                        .notIn("state", CollectTaskStateEnum.STOP, CollectTaskStateEnum.FINISHED));
        if (CollectionUtil.isNotEmpty(collectTasks)) {
            throw new ResultException(ResultEnum.TASK_IN_EXECUTE);
        }
        List<CollectTask> collectTaskList = new ArrayList<>();
        for (Integer sourceId : dto.getSourceIds()) {
            CollectTask collectTask = new CollectTask();
            collectTask.init(sourceId, dto.getDuration());
            collectTaskList.add(collectTask);
        }
        baseMapper.addCollectTasks(collectTaskList);
        return true;
    }

}
