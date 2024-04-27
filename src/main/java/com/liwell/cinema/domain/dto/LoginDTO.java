package com.liwell.cinema.domain.dto;

import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/17
 */
@Data
public class LoginDTO {

    private String username;

    private String password;

    private String captcha;

    private String phone;

    private String email;

}
