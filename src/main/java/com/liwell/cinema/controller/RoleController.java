package com.liwell.cinema.controller;

import cn.hutool.core.bean.BeanUtil;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.RoleListVO;
import com.liwell.cinema.service.RoleService;
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
 * @date Created on 2023/8/26
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/listRole")
    public Result<List<RoleListVO>> listRole() {
        return ResultUtil.success(BeanUtil.copyToList(roleService.list(), RoleListVO.class));
    }

}
