package com.liwell.cinema.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.ButtonPageDTO;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.PermissionAddDTO;
import com.liwell.cinema.domain.dto.PermissionUpdateDTO;
import com.liwell.cinema.domain.entity.RoleMenu;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.PermissionListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.service.PermissionService;
import com.liwell.cinema.service.RolePermissionService;
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
 * @author Li
 * @date Created on 2023/4/5
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/listUserPermission")
    public Result<List<Tree<Integer>>> listUserPermission() {
        return ResultUtil.success(permissionService.listUserPermission());
    }

    @PostMapping("/listMenu")
    public Result<List<Tree<Integer>>> listMenu() {
        return ResultUtil.success(permissionService.listMenu());
    }

    @PostMapping("/pageButton")
    public Result<Page<PermissionListVO>> pageButton(@RequestBody ButtonPageDTO dto) {
        return ResultUtil.success(permissionService.pageButton(dto));
    }

    @PostMapping("/addPermission")
    public Result addPermission(@RequestBody @Valid PermissionAddDTO dto) {
        return ResultUtil.trueOrFalse(permissionService.addPermission(dto));
    }

    @PostMapping("/updatePermission")
    public Result updatePermission(@RequestBody @Valid PermissionUpdateDTO dto) {
        return ResultUtil.trueOrFalse(permissionService.updatePermission(dto));
    }

    @PostMapping("/deletePermission")
    public Result deletePermission(@RequestBody IdDTO dto) {
        if (CollectionUtil.isEmpty(dto.getIds())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        rolePermissionService.remove(new QueryWrapper<RoleMenu>().in("permission_id", dto.getIds()));
        return ResultUtil.trueOrFalse(permissionService.deletePermission(dto.getIds()));
    }

}
