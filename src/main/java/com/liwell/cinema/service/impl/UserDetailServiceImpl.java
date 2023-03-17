package com.liwell.cinema.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liwell.cinema.domain.entity.User;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import com.liwell.cinema.domain.po.LoginUser;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/16
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username).eq("state", StateEnum.VALID));
        if (Objects.isNull(user)) {
            throw new ResultException(ResultEnum.USER_PWD_ERROR);
        }
        return new LoginUser(user);
    }

}
