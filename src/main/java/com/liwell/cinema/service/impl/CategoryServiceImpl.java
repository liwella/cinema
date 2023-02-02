package com.liwell.cinema.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.CategoryAddDTO;
import com.liwell.cinema.domain.dto.CategoryMoveDTO;
import com.liwell.cinema.domain.entity.Category;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.CategoryListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.CategoryMapper;
import com.liwell.cinema.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<CategoryListVO> listCategory(Integer parent) {
        Map<String, Integer> params = new HashMap<>();
        params.put("parent", parent);
        List<Category> categories = baseMapper.selectList(
                new QueryWrapper<Category>().allEq(params).orderByAsc("sort"));
        if (CollectionUtil.isEmpty(categories)) {
            return new ArrayList<>();
        }
        List<CategoryListVO> result = BeanUtil.copyToList(categories, CategoryListVO.class);
        for (CategoryListVO categoryListVO : result) {
            categoryListVO.setChildren(listCategory(categoryListVO.getId()));
        }
        return result;
    }

    @Override
    public Boolean addCategory(CategoryAddDTO dto) {
        Integer sort;
        int level;
        Integer parentNumber = null;
        if (dto.getParent() == null) {
            level = 0;
            sort = baseMapper.getMaxSort(parentNumber);
        } else {
            Category parent = baseMapper.selectOne(new QueryWrapper<Category>().eq("id", dto.getParent()));
            level = parent.getLevel() + 1;
            sort = baseMapper.getMaxSort(dto.getParent());
            parentNumber = dto.getParent();
        }
        Category category = new Category();
        category.setName(dto.getName());
        if (sort == null) {
            sort = 0;
        }
        category.setSort(++sort);
        category.setParent(parentNumber);
        category.setLevel(level);
        baseMapper.insert(category);
        return true;
    }

    @Override
    @Transactional
    public Boolean moveCategory(CategoryMoveDTO dto) {
        Category category = baseMapper.selectById(dto.getId());
        if (category == null) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        Map<String, Integer> params = new HashMap<>();
        params.put("parent", category.getParent());
        if (dto.getUp()) {
            if (category.getSort() == 1) {
                throw new ResultException(ResultEnum.ALREADY_TOP);
            }
            params.put("sort", category.getSort() - 1);
            Category up = baseMapper.selectOne(new QueryWrapper<Category>().allEq(params));
            transferPosition(up, category);
        } else {
            if (category.getSort().equals(baseMapper.getMaxSort(category.getParent()))) {
                throw new ResultException(ResultEnum.ALREADY_TOP);
            }
            params.put("sort", category.getSort() + 1);
            Category down = baseMapper.selectOne(new QueryWrapper<Category>().allEq(params));
            transferPosition(category, down);
        }
        return true;
    }

    private void transferPosition(Category up, Category down) {
        baseMapper.update(null, new UpdateWrapper<Category>()
                .set("sort", down.getSort() - 1).eq("id", down.getId()));
        baseMapper.update(null, new UpdateWrapper<Category>()
                .set("sort", up.getSort() + 1).eq("id", up.getId()));
    }

}
