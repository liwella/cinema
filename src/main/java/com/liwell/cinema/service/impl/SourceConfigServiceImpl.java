package com.liwell.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.entity.SourceConfig;
import com.liwell.cinema.domain.vo.ScListSimpleVO;
import com.liwell.cinema.mapper.SourceConfigMapper;
import com.liwell.cinema.service.SourceConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/13
 */
@Service
public class SourceConfigServiceImpl extends ServiceImpl<SourceConfigMapper, SourceConfig> implements SourceConfigService {

    @Override
    public List<ScListSimpleVO> listSimpleSc() {
        return baseMapper.listSimpleSc();
    }
}
