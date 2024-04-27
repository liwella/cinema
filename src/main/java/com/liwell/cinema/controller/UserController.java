package com.liwell.cinema.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.*;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.LoginVO;
import com.liwell.cinema.domain.vo.UserGetVO;
import com.liwell.cinema.domain.vo.UserLoginVO;
import com.liwell.cinema.domain.vo.UserPageVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.service.UserService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Result<UserLoginVO> getLoginUser() {
        return ResultUtil.success(userService.getLoginUser());
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestBody IdDTO dto) {
        if (CollectionUtil.isEmpty(dto.getIds())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.trueOrFalse(userService.removeByIds(dto.getIds()));
    }

}
