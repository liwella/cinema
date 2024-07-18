package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.SourceConfigPageDTO;
import com.liwell.cinema.domain.entity.SourceConfig;
import com.liwell.cinema.domain.vo.ScListSimpleVO;
import com.liwell.cinema.domain.vo.SourceConfigPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Mapper
@Repository
public interface SourceConfigMapper extends BaseMapper<SourceConfig> {

    List<ScListSimpleVO> listSimpleSc();

    Page<SourceConfigPageVO> pageSourceConfig(SourceConfigPageDTO dto);

}
