package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.CategoryAddDTO;
import com.liwell.cinema.domain.dto.CategoryMoveDTO;
import com.liwell.cinema.domain.entity.Category;
import com.liwell.cinema.domain.vo.CategoryListVO;

import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
public interface CategoryService extends IService<Category> {

    List<CategoryListVO> listCategory(Integer parent);

    Boolean addCategory(CategoryAddDTO dto);

    Boolean moveCategory(CategoryMoveDTO dto);

}
