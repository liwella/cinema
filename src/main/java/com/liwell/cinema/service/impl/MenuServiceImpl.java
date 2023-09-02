package com.liwell.cinema.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.MenuAddDTO;
import com.liwell.cinema.domain.dto.MenuMoveDTO;
import com.liwell.cinema.domain.entity.Menu;
import com.liwell.cinema.domain.enums.MenuTypeEnum;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.MenuListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.MenuMapper;
import com.liwell.cinema.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<MenuListVO> listUserMenu() {
        Integer userId = StpUtil.getLoginIdAsInt();
        List<MenuListVO> menuList = baseMapper.listUserMenu(userId);
        return refactor(menuList);
    }

    private List<MenuListVO> refactor(List<MenuListVO> menuList) {
        if (CollectionUtil.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        List<MenuListVO> result = new ArrayList<>();
        for (MenuListVO menuListVO : menuList) {
            if (menuListVO.getLevel() != null && 0 == menuListVO.getLevel()) {
                result.add(menuListVO);
                findChildren(menuListVO, menuList);
            }
        }
        result = result.stream().sorted(Comparator.comparing(MenuListVO::getSort)).collect(Collectors.toList());
        return result;
    }

    private void findChildren(MenuListVO menu, List<MenuListVO> menuList) {
        if (menu.getType() == MenuTypeEnum.PERMISSION) {
            return;
        }
        List<MenuListVO> children = new ArrayList<>();
        for (MenuListVO menuListVO : menuList) {
            if (menu.getId().equals(menuListVO.getParentId())) {
                children.add(menuListVO);
                findChildren(menuListVO, menuList);
            }
        }
        children = children.stream().sorted(
                Comparator.comparing(MenuListVO::getSort, Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());
        menu.setChildren(children);
    }

    @Override
    public List<MenuListVO> listMenu() {
        List<MenuListVO> menuList = baseMapper.listMenu();
        return refactor(menuList);
    }

    @Override
    @Transactional
    public Boolean deleteMenu(List<Integer> ids) {
        baseMapper.deleteBatchIds(ids);
        List<Menu> children = baseMapper.selectList(new QueryWrapper<Menu>().in("parent_id", ids));
        if (CollectionUtil.isEmpty(children)) {
            return true;
        }
        List<Integer> childrenIds = children.stream().map(Menu::getId).collect(Collectors.toList());
        deleteMenu(childrenIds);
        return true;
    }

    @Override
    public Boolean addMenu(MenuAddDTO dto) {
        Menu menu = BeanUtil.copyProperties(dto, Menu.class);
        List<Menu> list;
        if (Objects.isNull(dto.getParentId())) {
            menu.setLevel(0);
            list = baseMapper.selectList(new QueryWrapper<Menu>().isNull("parent_id"));
        } else {
            Menu parentMenu = baseMapper.selectById(dto.getParentId());
            menu.setLevel(parentMenu.getLevel() + 1);
            list = baseMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", menu.getParentId()));
        }
        if (CollectionUtil.isEmpty(list)) {
            menu.setSort(1);
        } else {
            menu.setSort(list.size() + 1);
        }
        baseMapper.insert(menu);
        return true;
    }

    @Override
    @Transactional
    public Boolean moveMenu(MenuMoveDTO dto) {
        Menu menu = baseMapper.selectById(dto.getId());
        if (menu == null) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        Map<String, Integer> params = new HashMap<>();
        params.put("parent_id", menu.getParentId());
        if (dto.getUp()) {
            if (menu.getSort() == 1) {
                throw new ResultException(ResultEnum.ALREADY_TOP);
            }
            params.put("sort", menu.getSort() - 1);
            Menu up = baseMapper.selectOne(new QueryWrapper<Menu>().allEq(params));
            transferPosition(up, menu);
        } else {
            if (menu.getSort().equals(baseMapper.getMaxSort(menu.getParentId()))) {
                throw new ResultException(ResultEnum.ALREADY_TOP);
            }
            params.put("sort", menu.getSort() + 1);
            Menu down = baseMapper.selectOne(new QueryWrapper<Menu>().allEq(params));
            transferPosition(menu, down);
        }
        return true;
    }

    private void transferPosition(Menu up, Menu down) {
        baseMapper.update(null, new UpdateWrapper<Menu>()
                .set("sort", down.getSort() - 1).eq("id", down.getId()));
        baseMapper.update(null, new UpdateWrapper<Menu>()
                .set("sort", up.getSort() + 1).eq("id", up.getId()));
    }

}
