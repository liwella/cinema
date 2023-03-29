package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {



}
