package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.RoleCategoryUpdateDTO;
import com.liwell.cinema.domain.entity.RoleCategory;

/**
 * @author litianyi
 * @filename RoleCategoryService.java
 * @purpose
 * @history 2023/9/8 litianyi
 */
public interface RoleCategoryService extends IService<RoleCategory> {

    Boolean updateRoleCategory(RoleCategoryUpdateDTO dto);

}
