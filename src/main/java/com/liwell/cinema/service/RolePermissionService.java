package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.RolePermissionUpdateDTO;
import com.liwell.cinema.domain.entity.RolePermission;

/**
 * @author nxg产品线 litianyi
 * @company 深圳联软科技股份有限公司
 * @filename RoleMenuService.java
 * @purpose
 * @history 2023/8/29 litianyi
 */
public interface RolePermissionService extends IService<RolePermission> {

    Boolean updateRoleMenu(RolePermissionUpdateDTO dto);

}
