package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/4/1
 */
@Data
public class UserAddDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Integer roleId;

    private String email;

    private String phone;

}
