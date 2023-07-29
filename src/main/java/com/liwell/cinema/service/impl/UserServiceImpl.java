package com.liwell.cinema.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.LoginDTO;
import com.liwell.cinema.domain.dto.UserAddDTO;
import com.liwell.cinema.domain.dto.UserPageDTO;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.entity.User;
import com.liwell.cinema.domain.entity.UserRole;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import com.liwell.cinema.domain.vo.LoginVO;
import com.liwell.cinema.domain.vo.UserPageVO;
import com.liwell.cinema.domain.vo.UserVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.RoleMapper;
import com.liwell.cinema.mapper.UserMapper;
import com.liwell.cinema.mapper.UserRoleMapper;
import com.liwell.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public LoginVO login(LoginDTO dto) {
        User user = baseMapper.selectOne(new QueryWrapper<User>()
                .eq("username", dto.getUsername()).eq("state", StateEnum.VALID));
        if (Objects.isNull(user) || !dto.getPassword().equals(user.getPassword())) {
            throw new ResultException(ResultEnum.USER_PWD_ERROR);
        }
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(tokenInfo.getTokenValue());
        return loginVO;
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public List<String> listUserPermission(Integer userId) {
        return baseMapper.listUserPermission(userId);
    }

    @Override
    public List<String> listUserRole(Integer userId) {
        return baseMapper.listUserRole(userId);
    }

    @Override
    public void addUser(UserAddDTO userAddDTO) {
        Role role = roleMapper.selectById(userAddDTO.getRoleId());
        if (Objects.isNull(role)) {
            throw new ResultException(ResultEnum.NOT_ROLE_EXCEPTION);
        }
        User user = BeanUtil.copyProperties(userAddDTO, User.class, "roleId");
        baseMapper.insert(user);
        UserRole userRole = new UserRole(user.getId(), userAddDTO.getRoleId());
        userRoleMapper.insert(userRole);
    }

    @Override
    public Page<UserPageVO> pageUser(UserPageDTO dto) {
        return baseMapper.pageUser(dto);
    }

    @Override
    public UserVO getUser() {
        Integer userId = StpUtil.getLoginIdAsInt();
        User user = baseMapper.selectById(userId);
        return BeanUtil.copyProperties(user, UserVO.class);
    }
}
