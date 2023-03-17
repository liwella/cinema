package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.LoginDTO;
import com.liwell.cinema.domain.entity.User;
import com.liwell.cinema.domain.vo.LoginVO;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/17
 */
public interface UserService extends IService<User> {

    LoginVO login(LoginDTO dto);

}
