package com.liwell.cinema.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.LoginDTO;
import com.liwell.cinema.domain.dto.UserAddDTO;
import com.liwell.cinema.domain.dto.UserPageDTO;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.UserPageVO;
import com.liwell.cinema.service.UserService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result login(@RequestBody LoginDTO loginDTO) {
        return ResultUtil.success(userService.login(loginDTO));
    }

    @PostMapping("/logout")
    public Result logout() {
        userService.logout();
        return ResultUtil.success();
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody @Valid UserAddDTO userAddDTO) {
        userService.addUser(userAddDTO);
        return ResultUtil.success();
    }

    @PostMapping("/pageUser")
    public Result<Page<UserPageVO>> pageUser(@RequestBody UserPageDTO dto) {
        return ResultUtil.success(userService.pageUser(dto));
    }

}
