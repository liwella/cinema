package com.liwell.cinema.controller;

import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.po.CollectCategory;
import com.liwell.cinema.domain.po.CollectListResult;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.remote.SourceService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/2/2
 */
@RestController
@RequestMapping("/source")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @PostMapping("/listSourceCategory")
    public Result<List<CollectCategory>> listSourceCategory(@RequestBody IdDTO idDTO) {
        CollectListResult collectListResult = sourceService.sourceBaseInfo(idDTO.getId(), null);
        return ResultUtil.success(collectListResult.getCollectCategories());
    }

}
