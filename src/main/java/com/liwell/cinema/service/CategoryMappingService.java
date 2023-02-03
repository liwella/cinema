package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.CategoryMappingUpdateDTO;
import com.liwell.cinema.domain.entity.CategoryMapping;

import java.util.Map;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
public interface CategoryMappingService extends IService<CategoryMapping> {

    Boolean addOrUpdate(CategoryMappingUpdateDTO dto);

    Map<Integer, Integer> getCategoryMapping(Integer sourceId);

}
