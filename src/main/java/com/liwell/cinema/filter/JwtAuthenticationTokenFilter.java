package com.liwell.cinema.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.LoginUser;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.helper.RedisHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/17
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisHelper redisHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        JWT jwt = JWTUtil.parseToken(token);
        Integer userId = (Integer) jwt.getPayload("userId");
        LoginUser loginUser = redisHelper.getLoginUser(userId);
        if (Objects.isNull(loginUser)) {
            throw new ResultException(ResultEnum.TOKEN_ERROR);
        }
        UsernamePasswordAuthenticationToken upToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(upToken);
        filterChain.doFilter(request, response);
    }

}
