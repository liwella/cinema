package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.SourceConfigPageDTO;
import com.liwell.cinema.domain.dto.SourceConfigUpdateDTO;
import com.liwell.cinema.domain.entity.SourceConfig;
import com.liwell.cinema.domain.vo.ScListSimpleVO;
import com.liwell.cinema.domain.vo.SourceConfigPageVO;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/13
 */
public interface SourceConfigService extends IService<SourceConfig> {

    Page<SourceConfigPageVO> pageSourceConfig(SourceConfigPageDTO dto);

    List<ScListSimpleVO> listSimpleSc();

    Boolean updateSourceConfig(SourceConfigUpdateDTO dto);

    Boolean deleteSourceConfig(List<Integer> ids);

}
