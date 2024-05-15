package com.liwell.cinema.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.RoleListDTO;
import com.liwell.cinema.domain.dto.RoleUpdateDTO;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.entity.RolePermission;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.RoleListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.RoleMapper;
import com.liwell.cinema.service.RolePermissionService;
import com.liwell.cinema.service.RoleService;
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

    @Override
    public List<RoleListVO> listRole(RoleListDTO dto) {
        /*QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(dto.getEnable())) {
            queryWrapper.eq("enable", dto.getEnable());
        }
        if (Objects.nonNull(dto.getName())) {
            queryWrapper.like("name", "%" + dto.getName() + "%");
        }
        List<Role> roles = baseMapper.selectList(queryWrapper);
        if (!roles.isEmpty()) {
            return BeanUtil.copyToList(roles, RoleListVO.class);
        }*/
        return baseMapper.listRole(dto);
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
}
