package com.liwell.cinema.domain.entity;

import com.liwell.cinema.domain.enums.MenuTypeEnum;
import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
@Data
public class Menu {

    private Integer id;

    private String menuName;

    private String url;

    private String permission;

    private MenuTypeEnum type;

    private String icon;

    private Integer level;

    private Integer parentId;

    private Integer sort;

}
