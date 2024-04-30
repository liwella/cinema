package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.UserPageDTO;
import com.liwell.cinema.domain.dto.UserUpdateDTO;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.entity.User;
import com.liwell.cinema.domain.vo.UserPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/17
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    List<String> listUserPermission(Integer userId);

    List<Role> listUserRole(Integer userId);

    Page<UserPageVO> pageUser(UserPageDTO dto);

    Role getUserRole(Integer userId);

    void updateUser(UserUpdateDTO userUpdateDTO);

}
