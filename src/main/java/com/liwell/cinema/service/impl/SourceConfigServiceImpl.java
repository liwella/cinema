package com.liwell.cinema.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.SourceConfigUpdateDTO;
import com.liwell.cinema.domain.entity.CategoryMapping;
import com.liwell.cinema.domain.entity.Playlist;
import com.liwell.cinema.domain.entity.SourceConfig;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.ScListSimpleVO;
import com.liwell.cinema.domain.vo.SourceConfigListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.CategoryMappingMapper;
import com.liwell.cinema.mapper.MovieMapper;
import com.liwell.cinema.mapper.PlaylistMapper;
import com.liwell.cinema.mapper.SourceConfigMapper;
import com.liwell.cinema.service.SourceConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/13
 */
@Slf4j
@Service
public class SourceConfigServiceImpl extends ServiceImpl<SourceConfigMapper, SourceConfig> implements SourceConfigService {

    @Autowired
    private CategoryMappingMapper categoryMappingMapper;
    @Autowired
    private PlaylistMapper playlistMapper;
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Boolean updateSourceConfig(SourceConfigUpdateDTO dto) {
        SourceConfig sourceConfig = baseMapper.selectById(dto.getId());
        if (Objects.isNull(sourceConfig)) {
            throw new ResultException(ResultEnum.SOURCE_CONFIG_NOT_EXISTS);
        }
        baseMapper.update(null, new UpdateWrapper<SourceConfig>()
                .set("source_name", dto.getSourceName()).set("list_url", dto.getListUrl())
                .set("detail_url", dto.getDetailUrl()).set("state", dto.getState()).eq("id", dto.getId()));
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteSourceConfig(List<Integer> ids) {
        log.info("删除采集源");
        baseMapper.deleteBatchIds(ids);
        log.info("删除采集源分类映射");
        categoryMappingMapper.delete(new QueryWrapper<CategoryMapping>().in("source_id", ids));
        log.info("删除采集源视频播放列表");
        playlistMapper.delete(new QueryWrapper<Playlist>().in("source_id", ids));
        log.info("删除已无播放资源的影片");
        movieMapper.deleteNonSourceMovie();
        return true;
    }

    @Override
    public List<ScListSimpleVO> listSimpleSc() {
        return baseMapper.listSimpleSc();
    }

}
