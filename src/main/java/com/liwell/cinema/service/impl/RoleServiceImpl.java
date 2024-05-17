package com.liwell.cinema.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.*;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.entity.RolePermission;
import com.liwell.cinema.domain.entity.UserRole;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.RoleListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.RoleMapper;
import com.liwell.cinema.service.RolePermissionService;
import com.liwell.cinema.service.RoleService;
import com.liwell.cinema.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<RoleListVO> listRole(RoleListDTO dto) {
        return baseMapper.listRole(dto);
    }

    @Transactional
    @Override
    public Boolean addRole(RoleAddDTO dto) {
        Role role = BeanUtil.copyProperties(dto, Role.class);
        baseMapper.insert(role);
        if (CollectionUtil.isNotEmpty(dto.getPermissionIds())) {
            List<RolePermission> rolePermissionList = dto.getPermissionIds().stream().map((menuId) -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(role.getId());
                rolePermission.setPermissionId(menuId);
                return rolePermission;
            }).collect(Collectors.toList());
            rolePermissionService.saveBatch(rolePermissionList);
        }
        return true;
    }

    @Transactional
    @Override
    public Boolean updateRole(RoleUpdateDTO dto) {
        Role role = baseMapper.selectById(dto.getId());
        if (Objects.isNull(role)) {
            throw new ResultException(ResultEnum.NOT_ROLE_EXCEPTION);
        }
        baseMapper.updateRole(dto);
        rolePermissionService.remove(new QueryWrapper<RolePermission>().eq("role_id", dto.getId()));
        if (CollectionUtil.isNotEmpty(dto.getPermissionIds())) {
            List<RolePermission> rolePermissionList = dto.getPermissionIds().stream().map((menuId) -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(dto.getId());
                rolePermission.setPermissionId(menuId);
                return rolePermission;
            }).collect(Collectors.toList());
            rolePermissionService.saveBatch(rolePermissionList);
        }
        return true;
    }

    @Override
    public Boolean addRoleUser(RoleUserAddDTO dto) {
        List<Integer> existedUserIds = userRoleService.lambdaQuery()
                .select(UserRole::getUserId)
                .eq(UserRole::getRoleId, dto.getRoleId())
                .list().stream()
                .map(UserRole::getUserId).collect(Collectors.toList());
        CollUtil.removeWithAddIf(dto.getUserIds(), existedUserIds::contains);
        List<UserRole> addUserRoles = dto.getUserIds().stream()
                .map(userId -> new UserRole(userId, dto.getRoleId())).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(addUserRoles)) {
            userRoleService.saveBatch(addUserRoles);
        }
        return true;
    }

    @Override
    public Boolean deleteUserRole(RoleUserDeleteDTO dto) {
        List<Role> roles = list(new QueryWrapper<Role>().eq("id", dto.getRoleId()));
        if (CollectionUtil.isEmpty(roles)) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        return userRoleService.lambdaUpdate()
                .eq(UserRole::getRoleId, dto.getRoleId())
                .in(UserRole::getUserId, dto.getUserIds())
                .remove();
    }

}
