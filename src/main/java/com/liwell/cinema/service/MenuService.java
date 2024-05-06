package com.liwell.cinema.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.MenuAddDTO;
import com.liwell.cinema.domain.dto.MenuMoveDTO;
import com.liwell.cinema.domain.entity.Menu;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
public interface MenuService extends IService<Menu> {

    List<Tree<Long>> listUserMenu();

    Boolean addMenu(MenuAddDTO dto);

    List<Tree<Long>> listMenu();

    Boolean moveMenu(MenuMoveDTO dto);

    Boolean deleteMenu(List<Integer> ids);

}
