package com.liwell.cinema.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.LoginDTO;
import com.liwell.cinema.domain.dto.UserAddDTO;
import com.liwell.cinema.domain.dto.UserPageDTO;
import com.liwell.cinema.domain.dto.UserUpdateDTO;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.entity.User;
import com.liwell.cinema.domain.entity.UserRole;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import com.liwell.cinema.domain.vo.LoginVO;
import com.liwell.cinema.domain.vo.UserGetVO;
import com.liwell.cinema.domain.vo.UserLoginVO;
import com.liwell.cinema.domain.vo.UserPageVO;
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
    public Boolean addUser(UserAddDTO userAddDTO) {
        Role role = roleMapper.selectById(userAddDTO.getRole().getId());
        if (Objects.isNull(role)) {
            throw new ResultException(ResultEnum.NOT_ROLE_EXCEPTION);
        }
        List<User> userList = baseMapper.selectList(
                new QueryWrapper<User>().eq("username", userAddDTO.getUsername()));
        if (CollectionUtil.isNotEmpty(userList)) {
            throw new ResultException(ResultEnum.USER_EXISTED);
        }
        User user = BeanUtil.copyProperties(userAddDTO, User.class, "role");
        baseMapper.insert(user);
        UserRole userRole = new UserRole(user.getId(), userAddDTO.getRole().getId());
        userRoleMapper.insert(userRole);
        return true;
    }

    @Override
    public Page<UserPageVO> pageUser(UserPageDTO dto) {
        return baseMapper.pageUser(dto);
    }

    @Override
    public UserGetVO getUser(Integer id) {
        User user = baseMapper.selectById(id);
        if (Objects.isNull(user)) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        UserGetVO userGetVO = BeanUtil.copyProperties(user, UserGetVO.class);
        Role role = baseMapper.getUserRole(id);
        userGetVO.setRole(role);
        return userGetVO;
    }

    @Override
    public UserLoginVO getLoginUser() {
        Integer userId = StpUtil.getLoginIdAsInt();
        User user = baseMapper.selectById(userId);
        UserLoginVO userLoginVO = BeanUtil.copyProperties(user, UserLoginVO.class);
        Role role = baseMapper.getUserRole(userId);
        userLoginVO.setRole(role);
        return userLoginVO;
    }

    @Override
    public Boolean updateUser(UserUpdateDTO userUpdateDTO) {
        User user = baseMapper.selectById(userUpdateDTO.getId());
        if (Objects.isNull(user)) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        baseMapper.updateUser(userUpdateDTO);
        userRoleMapper.update(null, new UpdateWrapper<UserRole>()
                .set("role_id", userUpdateDTO.getRole().getId()).eq("user_id", userUpdateDTO.getId()));
        return true;
    }
}
