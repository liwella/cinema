package com.liwell.cinema.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.RoleAddDTO;
import com.liwell.cinema.domain.dto.RoleListDTO;
import com.liwell.cinema.domain.dto.RoleUpdateDTO;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.entity.RoleCategory;
import com.liwell.cinema.domain.entity.RoleMenu;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.RoleListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.service.RoleCategoryService;
import com.liwell.cinema.service.RolePermissionService;
import com.liwell.cinema.service.RoleService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
 * @date Created on 2023/8/26
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private RoleCategoryService roleCategoryService;

    @PostMapping("/listRole")
    public Result<List<RoleListVO>> listRole(@RequestBody RoleListDTO dto) {
        return ResultUtil.success(roleService.listRole(dto));
    }

    @PostMapping("/addRole")
    public Result addRole(@RequestBody @Valid RoleAddDTO dto) {
        List<Role> roles = roleService.list(new QueryWrapper<Role>().eq("code", dto.getCode()));
        if (CollectionUtil.isNotEmpty(roles)) {
            throw new ResultException(ResultEnum.ROLE_CODE_EXISTS);
        }
        return ResultUtil.trueOrFalse(roleService.save(BeanUtil.copyProperties(dto, Role.class, "id")));
    }

    @PostMapping("/updateRole")
    public Result updateRole(@RequestBody RoleUpdateDTO dto) {
        return ResultUtil.trueOrFalse(roleService.updateRole(dto));
    }

    @PostMapping("/deleteRole")
    @Transactional
    public Result deleteRole(@RequestBody IdDTO dto) {
        if (CollectionUtil.isEmpty(dto.getIds())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        rolePermissionService.remove(new QueryWrapper<RoleMenu>().in("role_id", dto.getIds()));
        roleCategoryService.remove(new QueryWrapper<RoleCategory>().in("role_id", dto.getIds()));
        return ResultUtil.trueOrFalse(roleService.removeByIds(dto.getIds()));
    }

}
