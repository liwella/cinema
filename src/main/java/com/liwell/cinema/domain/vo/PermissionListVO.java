package com.liwell.cinema.domain.vo;

import com.liwell.cinema.domain.enums.LayoutEnum;
import com.liwell.cinema.domain.enums.MethodEnum;
import com.liwell.cinema.domain.enums.PermissionTypeEnum;
import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/4/5
 */
@Data
public class PermissionListVO {

    private Integer id;

    private String name;

    private String code;

    private PermissionTypeEnum type;

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
