package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    Integer getMaxSort(Integer parent);

    List<Category> listUserCategory(Map<String, Integer> params);

}
