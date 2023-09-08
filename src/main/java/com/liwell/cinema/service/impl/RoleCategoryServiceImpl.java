package com.liwell.cinema.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.RoleCategoryUpdateDTO;
import com.liwell.cinema.domain.entity.RoleCategory;
import com.liwell.cinema.mapper.RoleCategoryMapper;
import com.liwell.cinema.service.RoleCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author litianyi
 * @filename RoleCategoryServiceImpl.java
 * @purpose
 * @history 2023/9/8 litianyi
 */
@Service
public class RoleCategoryServiceImpl extends ServiceImpl<RoleCategoryMapper, RoleCategory> implements RoleCategoryService {

    @Override
    @Transactional
    public Boolean updateRoleCategory(RoleCategoryUpdateDTO dto) {
        List<Integer> existedCategoryIds = baseMapper.selectList(new QueryWrapper<RoleCategory>()
                .eq("role_id", dto.getRoleId())).stream().map(RoleCategory::getCategoryId).collect(Collectors.toList());
        List<Integer> removeList = new ArrayList<>();
        for (int existedCategoryId : existedCategoryIds) {
            if (dto.getCategoryIds().contains(existedCategoryId)) {
                continue;
            }
            removeList.add(existedCategoryId);
        }
        if (CollectionUtil.isNotEmpty(removeList)) {
            baseMapper.delete(new QueryWrapper<RoleCategory>().in("category_id", removeList));
        }
        List<RoleCategory> addList = new ArrayList<>();
        for (int categoryId : dto.getCategoryIds()) {
            if (existedCategoryIds.contains(categoryId)) {
                continue;
            }
            addList.add(new RoleCategory(dto.getRoleId(), categoryId));
        }
        if (CollectionUtil.isNotEmpty(addList)) {
            saveBatch(addList);
        }
        return true;
    }

}
