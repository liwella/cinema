package com.liwell.cinema.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liwell.cinema.domain.dto.ClassMappingListDTO;
import com.liwell.cinema.domain.entity.ClassMapping;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.service.ClassMappingService;
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
@RequestMapping("/classMapping")
public class ClassMappingController {

    @Autowired
    private ClassMappingService classMappingService;

    @PostMapping("/listClassMapping")
    public Result<List<ClassMapping>> listClassMapping(@RequestBody @Valid ClassMappingListDTO dto) {
        return ResultUtil.success(classMappingService.list(
                new QueryWrapper<ClassMapping>().eq("source_id", dto.getSourceId())));
    }

}
