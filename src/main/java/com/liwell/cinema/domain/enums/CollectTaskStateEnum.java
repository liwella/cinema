package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/03
 */
@Getter
public enum CollectTaskStateEnum implements BaseEnum {

    NOT_START(0, "待开始"),
    IN_EXECUTE(1, "执行中"),
    SUSPEND(2, "已暂停"),
    STOP(3, "已停止"),
    FINISHED(4, "已完成")
    ;

    private Integer value;

    private String description;

    CollectTaskStateEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

}
