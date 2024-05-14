package com.liwell.cinema.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.RoleListDTO;
import com.liwell.cinema.domain.dto.RoleUpdateDTO;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.RoleListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.RoleMapper;
import com.liwell.cinema.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<RoleListVO> listRole(RoleListDTO dto) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(dto.getEnable())) {
            queryWrapper.eq("enable", dto.getEnable());
        }
        if (Objects.nonNull(dto.getName())) {
            queryWrapper.like("name", "%" + dto.getName() + "%");
        }
        List<Role> roles = baseMapper.selectList(queryWrapper);
        if (!roles.isEmpty()) {
            return BeanUtil.copyToList(roles, RoleListVO.class);
        }
        return Collections.emptyList();
    }

    @Override
    public Boolean updateRole(RoleUpdateDTO dto) {
        Role role = baseMapper.selectById(dto.getId());
        if (Objects.isNull(role)) {
            throw new ResultException(ResultEnum.NOT_ROLE_EXCEPTION);
        }
        baseMapper.updateRole(dto);
        return true;
    }
}
