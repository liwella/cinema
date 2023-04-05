package com.liwell.cinema.domain.vo;

import com.liwell.cinema.domain.enums.MenuTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/4/5
 */
@Data
public class MenuListVO {

    private Integer id;

    private String menuName;

    private String url;

    private String permission;

    private MenuTypeEnum type;

    private String icon;

    private Integer level;

    private Integer parentId;

    private Integer sort;

    private List<MenuListVO> children;

}
