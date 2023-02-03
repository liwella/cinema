package com.liwell.cinema.controller;

import com.liwell.cinema.domain.dto.CollectTaskAddDTO;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.service.CollectTaskService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/03
 */
@RestController
@RequestMapping("/collectTask")
public class CollectTaskController {

    @Autowired
    private CollectTaskService collectTaskService;

    @PostMapping("/addCollectTask")
    public Result addCollectTask(@RequestBody CollectTaskAddDTO dto) {
        return ResultUtil.trueOrFalse(collectTaskService.addCollectTask(dto));
    }

}