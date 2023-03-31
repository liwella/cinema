package com.liwell.cinema.handler;

import cn.dev33.satoken.stp.StpInterface;
import com.liwell.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
@Component
public class PermissionHandler implements StpInterface {

    @Autowired
    private UserService userService;

    @Override
    public List<String> getPermissionList(Object o, String loginType) {
        if (Objects.isNull(o)) {
            return null;
        }
        Integer userId = Integer.parseInt(o.toString());
        return userService.listUserPermission(userId);
    }

    @Override
    public List<String> getRoleList(Object o, String loginType) {
        if (Objects.isNull(o)) {
            return null;
        }
        Integer userId = Integer.parseInt(o.toString());
        return userService.listUserRole(userId);
    }

}
