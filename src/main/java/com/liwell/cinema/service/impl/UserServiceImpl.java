package com.liwell.cinema.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.constants.Constants;
import com.liwell.cinema.domain.dto.LoginDTO;
import com.liwell.cinema.domain.entity.User;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.LoginUser;
import com.liwell.cinema.domain.vo.LoginVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.helper.RedisHelper;
import com.liwell.cinema.mapper.UserMapper;
import com.liwell.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisHelper redisHelper;

    @Override
    public LoginVO login(LoginDTO dto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        if (Objects.isNull(authenticate)) {
            throw new ResultException(ResultEnum.USER_PWD_ERROR);
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Integer userId = loginUser.getId();
        redisHelper.setLoginUser(userId, loginUser);

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("expire_time", System.currentTimeMillis() + 60 * 60 * 1000);
        String jwtToken = JWTUtil.createToken(map, Constants.JWT_KEY.getBytes());
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(jwtToken);
        return loginVO;
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userId = loginUser.getId();
        redisHelper.removeLoginUser(userId);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
