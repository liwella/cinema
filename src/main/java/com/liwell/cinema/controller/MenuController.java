package com.liwell.cinema.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liwell.cinema.domain.dto.IdDTO;
import com.liwell.cinema.domain.dto.MenuAddDTO;
import com.liwell.cinema.domain.dto.MenuMoveDTO;
import com.liwell.cinema.domain.dto.MenuUpdateDTO;
import com.liwell.cinema.domain.entity.Menu;
import com.liwell.cinema.domain.entity.RoleMenu;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.service.MenuService;
import com.liwell.cinema.service.RoleMenuService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    @Autowired
    private RoleMenuService roleMenuService;

    @PostMapping("/listUserMenu")
    public Result<List<Tree<Long>>> listUserMenu() {
        return ResultUtil.success(menuService.listUserMenu());
    }

    @PostMapping("/listMenu")
    public Result<List<Tree<Long>>> listMenu() {
        return ResultUtil.success(menuService.listMenu());
    }

    @PostMapping("/addMenu")
    public Result addMenu(@RequestBody @Valid MenuAddDTO dto) {
        return ResultUtil.trueOrFalse(menuService.addMenu(dto));
    }

    @PostMapping("/updateMenu")
    public Result updateMenu(@RequestBody @Valid MenuUpdateDTO dto) {
        return ResultUtil.trueOrFalse(menuService.update(new UpdateWrapper<Menu>()
                .set("menu_name", dto.getMenuName())
                .set("url", dto.getUrl())
                .set("permission", dto.getPermission())
                .set("type", dto.getType())
                .set("icon", dto.getIcon())
                .eq("id", dto.getId())));
    }

    @PostMapping("/moveMenu")
    public Result moveMenu(@RequestBody @Valid MenuMoveDTO dto) {
        return ResultUtil.trueOrFalse(menuService.moveMenu(dto));
    }

    @PostMapping("/deleteMenu")
    public Result deleteMenu(@RequestBody IdDTO dto) {
        if (CollectionUtil.isEmpty(dto.getIds())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        roleMenuService.remove(new QueryWrapper<RoleMenu>().in("menu_id", dto.getIds()));
        return ResultUtil.trueOrFalse(menuService.deleteMenu(dto.getIds()));
    }

}
