package com.liwell.cinema.domain.vo;

import com.liwell.cinema.domain.enums.LayoutEnum;
import com.liwell.cinema.domain.enums.MenuTypeEnum;
import com.liwell.cinema.domain.enums.MethodEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/4/5
 */
@Data
public class MenuListVO {

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

    private StateEnum keepAlive;

    private MethodEnum method;

    private String description;

    private StateEnum enable;

    private Integer sort;

}
