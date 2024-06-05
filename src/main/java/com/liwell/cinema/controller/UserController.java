package com.liwell.cinema.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.*;
import com.liwell.cinema.domain.entity.UserRole;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.LoginVO;
import com.liwell.cinema.domain.vo.UserGetVO;
import com.liwell.cinema.domain.vo.UserPageVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.service.UserRoleService;
import com.liwell.cinema.service.UserService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        return ResultUtil.success(userService.login(loginDTO));
    }

    @GetMapping("/refreshToken")
    public Result<LoginVO> refreshToken() {
        return ResultUtil.success(userService.refreshToken());
    }

    @PostMapping("/logout")
    public Result logout() {
        userService.logout();
        return ResultUtil.success();
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody @Valid UserAddDTO userAddDTO) {
        return ResultUtil.trueOrFalse(userService.addUser(userAddDTO));
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return ResultUtil.trueOrFalse(userService.updateUser(userUpdateDTO));
    }

    @PostMapping("/pageUser")
    public Result<Page<UserPageVO>> pageUser(@RequestBody UserPageDTO dto) {
        return ResultUtil.success(userService.pageUser(dto));
    }

    @PostMapping("/getUser")
    public Result<UserGetVO> getUser(@RequestBody IdDTO dto) {
        return ResultUtil.success(userService.getUser(dto.getId()));
    }

    @PostMapping("/getLoginUser")
    public Result<UserGetVO> getLoginUser() {
        Integer userId = StpUtil.getLoginIdAsInt();
        return ResultUtil.success(userService.getUser(userId));
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestBody IdDTO dto) {
        if (CollectionUtil.isEmpty(dto.getIds())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        userService.removeByIds(dto.getIds());
        userRoleService.lambdaUpdate().in(UserRole::getUserId, dto.getIds()).remove();
        return ResultUtil.success();
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        Pair<String, ICaptcha> captchaPair = userService.createCaptcha();
        captchaPair.getValue().write(response.getOutputStream());
    }

}
