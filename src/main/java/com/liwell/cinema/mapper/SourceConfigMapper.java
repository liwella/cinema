package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.entity.SourceConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Mapper
@Repository
public interface SourceConfigMapper extends BaseMapper<SourceConfig> {


}
