package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.entity.Menu;
import com.liwell.cinema.domain.vo.MenuListVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuListVO> listUserMenu(Integer userId);

    List<MenuListVO> listMenu();

}
