package com.liwell.cinema.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/8/26
 */
@Data
public class RoleListVO {

    private Integer id;

    private String name;

    private String code;

    private Boolean enable;

    private List<Integer> permissionIds;

}
