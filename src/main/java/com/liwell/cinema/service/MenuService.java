package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.IdDTO;
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

    List<MenuListVO> listMenu();

}
