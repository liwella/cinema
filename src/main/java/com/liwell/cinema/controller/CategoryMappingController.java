package com.liwell.cinema.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liwell.cinema.domain.dto.CategoryMappingDeleteDTO;
import com.liwell.cinema.domain.dto.CategoryMappingListDTO;
import com.liwell.cinema.domain.dto.CategoryMappingUpdateDTO;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.entity.CategoryMapping;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.service.CategoryMappingService;
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
@RequestMapping("/categoryMapping")
public class CategoryMappingController {

    @Autowired
    private CategoryMappingService categoryMappingService;

    @PostMapping("/listCategoryMapping")
    public Result<List<CategoryMapping>> listCategoryMapping(@RequestBody @Valid CategoryMappingListDTO dto) {
        return ResultUtil.success(categoryMappingService.list(
                new QueryWrapper<CategoryMapping>().eq("source_id", dto.getSourceId())));
    }

    @PostMapping("/addOrUpdate")
    public Result addOrUpdate(@RequestBody @Valid CategoryMappingUpdateDTO dto) {
        return ResultUtil.trueOrFalse(categoryMappingService.addOrUpdate(dto));
    }

    @PostMapping("/delete")
    public Result deleteCategoryMapping(@RequestBody @Valid CategoryMappingDeleteDTO dto) {
        boolean remove = categoryMappingService.remove(new QueryWrapper<CategoryMapping>()
                .eq("source_id", dto.getSourceId()).eq("source_type_id", dto.getSourceTypeId()));
        return ResultUtil.trueOrFalse(remove);
    }

}
