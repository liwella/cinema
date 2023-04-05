package com.liwell.cinema.controller;

import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.MenuListVO;
import com.liwell.cinema.service.MenuService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/4/5
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/listMenu")
    public Result<List<MenuListVO>> listMenu(@RequestBody IdDTO dto) {
        return ResultUtil.success(menuService.listMenu(dto));
    }


}
