package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.*;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.vo.RoleListVO;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
public interface RoleService extends IService<Role> {

    List<RoleListVO> listRole(RoleListDTO dto);

    Boolean addRole(RoleAddDTO dto);

    Boolean updateRole(RoleUpdateDTO dto);

    Boolean addRoleUser(RoleUserAddDTO dto);

    Boolean deleteUserRole(RoleUserDeleteDTO dto);

}
