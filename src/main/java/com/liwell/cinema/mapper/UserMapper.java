package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.entity.User;
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

    List<String> listUserRole(Integer userId);

}
