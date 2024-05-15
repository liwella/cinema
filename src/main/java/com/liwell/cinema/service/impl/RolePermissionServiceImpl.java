package com.liwell.cinema.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.RolePermissionUpdateDTO;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.entity.RolePermission;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.RoleMapper;
import com.liwell.cinema.mapper.RolePermissionMapper;
import com.liwell.cinema.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author nxg产品线 litianyi
 * @company 深圳联软科技股份有限公司
 * @filename RoleMenuServiceImpl.java
 * @purpose
 * @history 2023/8/29 litianyi
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Boolean updateRoleMenu(RolePermissionUpdateDTO dto) {
        Role role = roleMapper.selectById(dto.getRoleId());
        if (Objects.isNull(role)) {
            throw new ResultException(ResultEnum.NOT_ROLE_EXCEPTION);
        }
        baseMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", dto.getRoleId()));
        if (CollectionUtil.isNotEmpty(dto.getPermissionIds())) {
            List<RolePermission> rolePermissionList = dto.getPermissionIds().stream().map((menuId) -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(dto.getRoleId());
                rolePermission.setPermissionId(menuId);
                return rolePermission;
            }).collect(Collectors.toList());
            this.saveBatch(rolePermissionList);
        }
        return true;
    }

}
