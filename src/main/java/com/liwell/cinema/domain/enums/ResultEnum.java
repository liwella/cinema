package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Getter
public enum ResultEnum implements BaseEnum {

    // 通用异常
    SUCCESS(200, "操作成功"),
    FAIL(-1, "操作失败"),
    DATA_NOT_EXIST(1001, "查询数据出错！"),
    PARAMETER_ERROR(1002, "参数错误！"),
    ENUM_VALUE_ERROR(1003, "枚举值不存在"),
    INTERFACE_METHOD_ERROR(1004, "接口不支持的方法"),
    THIRD_INTERFACE_ERROR(1005, "调用第三方接口出错！"),

    // 用户模块
    USER_PWD_ERROR(1101, "用户名或秘密错误！"),
    LOGGING_ERROR(1102, "登录信息错误"),
    PERMISSION_ERROR(1103, "权限不足！"),
    NOT_ROLE_EXCEPTION(1104, "角色不存在"),

    // 分类模块
    ALREADY_TOP(1201, "已是最上层/底层节点，无法移动"),
    CHILDREN_EXISTS(1202, "有节点存在子分类未删除，请先删除子分类"),

    // 影视模块
    TASK_IN_EXECUTE(1303, "采集源当前有采集任务在执行中，请勿重复执行"),
    TASK_STATE_ERROR(1304, "当前任务状态无法执行")
    ;

    private Integer value;

    private String message;

    ResultEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

}
