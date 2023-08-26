package com.liwell.cinema.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.enums.SexEnum;
import com.liwell.cinema.domain.enums.StateEnum;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/4/1
 */
@Data
public class UserPageVO {

    private Integer id;

    private String username;

    private String email;

    private String phone;

    private String avatar;

    private SexEnum sex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private StateEnum state;

    private Role role;

}
