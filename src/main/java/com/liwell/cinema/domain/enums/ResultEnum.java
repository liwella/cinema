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

    SUCCESS(0, "操作成功"),
    FAIL(-1, "操作失败"),
    DATA_NOT_EXIST(1001, "查询数据出错！"),
    PARAMETER_ERROR(1002, "参数错误！"),
    ENUM_VALUE_ERROR(1003, "枚举值不存在"),

    USER_PWD_ERROR(1090, "用户名或秘密错误！"),
    TOKEN_ERROR(1091, "token 不合法！"),
    THIRD_INTERFACE_ERROR(1100, "调用第三方接口出错！"),
    ALREADY_TOP(1101, "已是最上层/底层节点，无法移动"),
    CHILDREN_EXISTS(1102, "有节点存在子分类未删除，请先删除子分类"),
    TASK_IN_EXECUTE(1103, "采集源当前有采集任务在执行中，请勿重复执行"),
    TASK_STATE_ERROR(1104, "当前任务状态无法执行")
    ;

    private Integer value;

    private String message;

    ResultEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

}
