package com.liwell.cinema.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.RoleCategoryUpdateDTO;
import com.liwell.cinema.domain.entity.RoleCategory;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.RoleCategoryListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.service.RoleCategoryService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author litianyi
 * @filename RoleCategoryController.java
 * @purpose
 * @history 2023/9/8 litianyi
 */
@RestController
@RequestMapping("/roleCategory")
public class RoleCategoryController {

    @Autowired
    private RoleCategoryService roleCategoryService;

    @PostMapping("/listRoleCategory")
    public Result<List<RoleCategoryListVO>> listRoleCategory(@RequestBody IdDTO dto) {
        if (Objects.isNull(dto.getId())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success(BeanUtil.copyToList(roleCategoryService
                .list(new QueryWrapper<RoleCategory>().eq("role_id", dto.getId())), RoleCategoryListVO.class));
    }

    @PostMapping("/updateRoleCategory")
    public Result updateRoleCategory(@RequestBody @Valid RoleCategoryUpdateDTO dto) {
        if (CollectionUtil.isEmpty(dto.getCategoryIds())) {
            dto.setCategoryIds(new ArrayList<>());
        }
        return ResultUtil.trueOrFalse(roleCategoryService.updateRoleCategory(dto));
    }

}
