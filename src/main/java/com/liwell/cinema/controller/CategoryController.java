package com.liwell.cinema.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liwell.cinema.domain.dto.CategoryAddDTO;
import com.liwell.cinema.domain.dto.CategoryMoveDTO;
import com.liwell.cinema.domain.dto.CategoryUpdateDTO;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.entity.Category;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.CategoryListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.service.CategoryService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类展示
     * @return
     */
    @PostMapping("/listCategory")
    public Result<List<CategoryListVO>> listCategory() {
        return ResultUtil.success(categoryService.listCategory(null));
    }

    /**
     * 新增分类
     * @param dto
     * @return
     */
    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody @Valid CategoryAddDTO dto) {
        return ResultUtil.trueOrFalse(categoryService.addCategory(dto));
    }

    /**
     * 上下移动分类
     * @param dto
     * @return
     */
    @PostMapping("/moveCategory")
    public Result moveCategory(@RequestBody @Valid CategoryMoveDTO dto) {
        return ResultUtil.trueOrFalse(categoryService.moveCategory(dto));
    }

    /**
     * 更新分类
     * @param dto
     * @return
     */
    @PostMapping("/updateCategory")
    public Result updateCategory(@RequestBody @Valid CategoryUpdateDTO dto) {
        categoryService.update(new UpdateWrapper<Category>()
                .set("name", dto.getName()).eq("id", dto.getId()));
        return ResultUtil.success();
    }

    /**
     * 删除分类
     * @param dto
     * @return
     */
    @PostMapping("/deleteCategory")
    public Result deleteCategory(@RequestBody IdDTO dto) {
        List<Category> children = categoryService.list(new QueryWrapper<Category>().in("parent", dto.getIds()));
        if (CollectionUtil.isNotEmpty(children)) {
            throw new ResultException(ResultEnum.CHILDREN_EXISTS);
        }
        return ResultUtil.trueOrFalse(categoryService.removeByIds(dto.getIds()));
    }

}