package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.CollectTaskPageDTO;
import com.liwell.cinema.domain.entity.CollectTask;
import com.liwell.cinema.domain.vo.CollectTaskPageVO;
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

    Page<CollectTaskPageVO> pageCollectTask(CollectTaskPageDTO dto);

}
