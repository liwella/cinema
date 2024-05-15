package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author nxg产品线 litianyi
 * @company 深圳联软科技股份有限公司
 * @filename RoleMenuMapper.java
 * @purpose
 * @history 2023/8/29 litianyi
 */
@Mapper
@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermission> {



}
