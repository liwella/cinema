package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.LoginDTO;
import com.liwell.cinema.domain.dto.UserAddDTO;
import com.liwell.cinema.domain.dto.UserPageDTO;
import com.liwell.cinema.domain.dto.UserUpdateDTO;
import com.liwell.cinema.domain.entity.User;
import com.liwell.cinema.domain.vo.LoginVO;
import com.liwell.cinema.domain.vo.UserGetVO;
import com.liwell.cinema.domain.vo.UserLoginVO;
import com.liwell.cinema.domain.vo.UserPageVO;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/17
 */
public interface UserService extends IService<User> {

    LoginVO login(LoginDTO dto);

    void logout();

    List<String> listUserPermission(Integer userId);

    List<String> listUserRole(Integer userId);

    Boolean addUser(UserAddDTO userAddDTO);

    Page<UserPageVO> pageUser(UserPageDTO dto);

    UserLoginVO getLoginUser();

    Boolean updateUser(UserUpdateDTO userUpdateDTO);

    UserGetVO getUser(Integer id);

}
