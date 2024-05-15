package com.liwell.cinema.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.ButtonPageDTO;
import com.liwell.cinema.domain.dto.PermissionAddDTO;
import com.liwell.cinema.domain.dto.PermissionUpdateDTO;
import com.liwell.cinema.domain.entity.Permission;
import com.liwell.cinema.domain.vo.PermissionListVO;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
public interface PermissionService extends IService<Permission> {

    List<Tree<Integer>> listUserPermission();

    Boolean addPermission(PermissionAddDTO dto);

    Boolean updatePermission(PermissionUpdateDTO dto);

    List<Tree<Integer>> listMenu();

    Boolean deletePermission(List<Integer> ids);

    Page<PermissionListVO> pageButton(ButtonPageDTO dto);

}
