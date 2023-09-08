package com.liwell.cinema.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author litianyi
 * @filename RoleCategory.java
 * @purpose
 * @history 2023/9/8 litianyi
 */
@Data
@NoArgsConstructor
public class RoleCategory {

    private Integer id;

    private Integer roleId;

    private Integer categoryId;

    public RoleCategory(Integer roleId, Integer categoryId) {
        this.roleId = roleId;
        this.categoryId = categoryId;
    }

}