package com.liwell.cinema.controller;

import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.ScListSimpleVO;
import com.liwell.cinema.service.SourceConfigService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/13
 */
@RestController
@RequestMapping("/sourceConfig")
public class SourceConfigController {

    @Autowired
    private SourceConfigService sourceConfigService;

    @PostMapping("/listSimpleSc")
    public Result<List<ScListSimpleVO>> listSimpleSc() {
        return ResultUtil.success(sourceConfigService.listSimpleSc());
    }

}
