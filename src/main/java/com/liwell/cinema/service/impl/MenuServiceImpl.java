package com.liwell.cinema.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.constants.Constants;
import com.liwell.cinema.domain.dto.MenuAddDTO;
import com.liwell.cinema.domain.dto.MenuChildPageDTO;
import com.liwell.cinema.domain.dto.MenuUpdateDTO;
import com.liwell.cinema.domain.entity.Menu;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.MenuListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.MenuMapper;
import com.liwell.cinema.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    public List<Tree<Integer>> listUserMenu() {
        if (Constants.ROLE_ADMIN.equals(StpUtil.getSession().get(Constants.CURRENT_ROLE_KEY))) {
            return listMenu();
        }
        Integer userId = StpUtil.getLoginIdAsInt();
        List<MenuListVO> menuList = baseMapper.listUserMenu(userId);
        return refactor(menuList);
    }

    @Override
    public List<Tree<Integer>> listMenu() {
        List<MenuListVO> menuList = baseMapper.listMenu();
        return refactor(menuList);
    }

    private List<Tree<Integer>> refactor(List<MenuListVO> menuList) {
        List<TreeNode<Integer>> nodes = menuList.stream().map(menu -> {
            TreeNode<Integer> treeNode = new TreeNode<>();
            treeNode.setId(menu.getId());
            treeNode.setParentId(menu.getParentId());
            treeNode.setWeight(menu.getSort());
            treeNode.setName(menu.getName());
            treeNode.setExtra(BeanUtil.beanToMap(menu));
            return treeNode;
        }).collect(Collectors.toList());
        return TreeUtil.build(nodes, null);
    }

    /* 旧版重构菜单
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
    }*/

    @Override
    public Page<MenuListVO> pageChildMenu(MenuChildPageDTO dto) {
        if (Objects.isNull(dto.getParentId())) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        return baseMapper.pageChildMenu(dto);
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
        if (Objects.nonNull(dto.getParentId())) {
            Menu parent = baseMapper.selectById(dto.getParentId());
            dto.getType().validate(parent.getType());
        } else {
            dto.getType().validate(null);
        }
        Menu menu = BeanUtil.copyProperties(dto, Menu.class);
        baseMapper.insert(menu);
        return true;
    }

    @Override
    public Boolean updateMenu(MenuUpdateDTO dto) {
        if (Objects.nonNull(dto.getParentId())) {
            Menu parent = baseMapper.selectById(dto.getParentId());
            dto.getType().validate(parent.getType());
        } else {
            dto.getType().validate(null);
        }
        this.update(new UpdateWrapper<Menu>()
                .set("parent_id", dto.getParentId()).set("name", dto.getName())
                .set("code", dto.getCode()).set("path", dto.getPath())
                .set("icon", dto.getIcon()).set("layout", dto.getLayout())
                .set("component", dto.getComponent()).set("display", dto.getDisplay())
                .set("enable", dto.getEnable()).set("keep_alive", dto.getKeepAlive())
                .set("type", dto.getType()).eq("id", dto.getId()));
        return true;
    }

}
