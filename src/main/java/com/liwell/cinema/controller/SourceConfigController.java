package com.liwell.cinema.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.SourceConfigAddDTO;
import com.liwell.cinema.domain.dto.SourceConfigListDTO;
import com.liwell.cinema.domain.dto.SourceConfigUpdateDTO;
import com.liwell.cinema.domain.entity.SourceConfig;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.ScListSimpleVO;
import com.liwell.cinema.domain.vo.SourceConfigListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.service.SourceConfigService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/13
 */
@RestController
@RequestMapping("/sourceConfig")
public class SourceConfigController {

    @Autowired
    private SourceConfigService sourceConfigService;

    /**
     * 采集源列表
     * @return
     */
    @PostMapping("/list")
    public Result<List<SourceConfigListVO>> listSourceConfig(@RequestBody SourceConfigListDTO dto) {
        QueryWrapper<SourceConfig> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(dto.getState())) {
            queryWrapper.eq("state", dto.getState());
        }
        List<SourceConfig> list = sourceConfigService.list(queryWrapper);
        return ResultUtil.success(BeanUtil.copyToList(list, SourceConfigListVO.class));
    }

    /**
     * 新增采集源
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public Result addSourceConfig(@RequestBody SourceConfigAddDTO dto) {
        SourceConfig sourceConfig = BeanUtil.copyProperties(dto, SourceConfig.class);
        sourceConfig.setState(StateEnum.VALID);
        return ResultUtil.trueOrFalse(sourceConfigService.save(sourceConfig));
    }

    /**
     * 更新采集源
     * @param dto
     * @return
     */
    @PostMapping("/update")
    public Result updateSourceConfig(@RequestBody SourceConfigUpdateDTO dto) {
        return ResultUtil.trueOrFalse(sourceConfigService.updateSourceConfig(dto));
    }

    /**
     * 删除采集源
     * @param dto
     * @return
     */
    @PostMapping("/delete")
    public Result deleteSourceConfig(@RequestBody IdDTO dto) {
        if (CollectionUtil.isEmpty(dto.getIds())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.trueOrFalse(sourceConfigService.deleteSourceConfig(dto.getIds()));
    }

    /**
     * 采集源选择列表
     * @return
     */
    @PostMapping("/listSimpleSc")
    public Result<List<ScListSimpleVO>> listSimpleSc() {
        return ResultUtil.success(sourceConfigService.listSimpleSc());
    }

}
