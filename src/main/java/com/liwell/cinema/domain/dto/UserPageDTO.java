package com.liwell.cinema.domain.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class UserPageDTO extends Page {

    private String username;

    private String email;

    private String phone;

    private Date startTime;

    private Date endTime;

    private SexEnum sex;

    private StateEnum state;

}
