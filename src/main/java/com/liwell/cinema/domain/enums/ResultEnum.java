package com.liwell.cinema.domain.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
public enum ResultEnum implements IEnum<Integer> {

    SUCCESS(0, "操作成功"),
    FAIL(-1, "操作失败"),
    DATA_NOT_EXIST(1001, "查询数据出错！"),

    THIRD_INTERFACE_ERROR(1002, "调用第三方接口出错！")
    ;

    private Integer value;

    private String message;

    ResultEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}
