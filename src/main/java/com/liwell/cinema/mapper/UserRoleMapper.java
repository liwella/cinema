package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/4/1
 */
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {



}
