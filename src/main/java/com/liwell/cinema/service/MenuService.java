package com.liwell.cinema.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.MenuAddDTO;
import com.liwell.cinema.domain.dto.MenuChildPageDTO;
import com.liwell.cinema.domain.dto.MenuMoveDTO;
import com.liwell.cinema.domain.entity.Menu;
import com.liwell.cinema.domain.vo.MenuListVO;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
public interface MenuService extends IService<Menu> {

    List<Tree<Integer>> listUserMenu();

    Boolean addMenu(MenuAddDTO dto);

    List<Tree<Integer>> listMenu();

    Boolean moveMenu(MenuMoveDTO dto);

    Boolean deleteMenu(List<Integer> ids);

    Page<MenuListVO> pageChildMenu(MenuChildPageDTO dto);

}
