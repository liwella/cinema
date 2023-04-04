package com.liwell.cinema.domain.entity;

import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/4/1
 */
@Data
public class UserRole {

    private Integer id;

    private Integer userId;

    private Integer roleId;

    public UserRole() {
    }

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

}
