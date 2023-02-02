package com.liwell.cinema.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.CategoryMappingUpdateDTO;
import com.liwell.cinema.domain.entity.Category;
import com.liwell.cinema.domain.entity.CategoryMapping;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.CategoryMapper;
import com.liwell.cinema.mapper.CategoryMappingMapper;
import com.liwell.cinema.service.CategoryMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@Service
public class CategoryMappingServiceImpl extends ServiceImpl<CategoryMappingMapper, CategoryMapping> implements CategoryMappingService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Boolean addOrUpdate(CategoryMappingUpdateDTO dto) {
        Category category = categoryMapper.selectById(dto.getCategoryId());
        if (category == null) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        Map<String, Integer> params = new HashMap<>();
        params.put("source_id", dto.getSourceId());
        params.put("source_type_id", dto.getSourceTypeId());
        CategoryMapping categoryMapping = baseMapper.selectOne(new QueryWrapper<CategoryMapping>().allEq(params));
        if (categoryMapping == null) {
            CategoryMapping cm = new CategoryMapping();
            cm.setCategoryId(dto.getCategoryId());
            cm.setCategoryName(category.getName());
            cm.setSourceId(dto.getSourceId());
            cm.setSourceTypeId(dto.getSourceTypeId());
            baseMapper.insert(cm);
            return true;
        }
        baseMapper.update(null, new UpdateWrapper<CategoryMapping>()
                .set("category_id", category.getId()).set("category_name", category.getName()).allEq(params));
        return true;
    }

}
