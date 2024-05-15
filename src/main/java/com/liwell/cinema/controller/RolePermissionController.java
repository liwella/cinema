package com.liwell.cinema.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.RoleMenuUpdateDTO;
import com.liwell.cinema.domain.entity.RoleMenu;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.RoleMenuListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.service.RolePermissionService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author nxg产品线 litianyi
 * @company 深圳联软科技股份有限公司
 * @filename RoleMenuController.java
 * @purpose
 * @history 2023/8/29 litianyi
 */
@RestController
@RequestMapping("/roleMenu")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/listRoleMenu")
    public Result<List<RoleMenuListVO>> listRoleMenu(@RequestBody IdDTO dto) {
        if (Objects.isNull(dto.getId())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success(BeanUtil.copyToList(rolePermissionService
                .list(new QueryWrapper<RoleMenu>().eq("role_id", dto.getId())), RoleMenuListVO.class));
    }

    @PostMapping("/updateRoleMenu")
    public Result updateRoleMenu(@RequestBody @Valid RoleMenuUpdateDTO dto) {
        return ResultUtil.trueOrFalse(rolePermissionService.updateRoleMenu(dto));
    }

}
