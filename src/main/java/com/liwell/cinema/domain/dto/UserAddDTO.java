package com.liwell.cinema.domain.dto;

import com.liwell.cinema.domain.enums.SexEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/4/1
 */
@Data
public class UserAddDTO {

    @NotNull(message = "用户名称不能为空")
    private String username;

    @NotNull(message = "用户密码不能为空")
    private String password;

    private String email;

    private String phone;

    private String avatar;

    private SexEnum sex;

    @NotNull(message = "角色不能为空")
    private List<Integer> roleIds;

    @NotNull(message = "用户状态不能为空")
    private Boolean state;

}
