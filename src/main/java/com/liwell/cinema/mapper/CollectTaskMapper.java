package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.entity.CollectTask;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/03
 */
@Mapper
@Repository
public interface CollectTaskMapper extends BaseMapper<CollectTask> {

    void addCollectTasks(List<CollectTask> collectTaskList);

}
