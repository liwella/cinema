package com.liwell.cinema.domain.entity;

import com.liwell.cinema.domain.enums.LayoutEnum;
import com.liwell.cinema.domain.enums.MenuTypeEnum;
import com.liwell.cinema.domain.enums.MethodEnum;
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

    private String name;

    private String code;

    private MenuTypeEnum type;

    private Integer parentId;

    private String path;

    private String redirect;

    private String icon;

    private String component;

    private LayoutEnum layout;

    private Boolean keepAlive;

    private MethodEnum method;

    private String description;

    private Boolean display;

    private Boolean enable;

    private Integer sort;

}
