package com.liwell.cinema.domain.po;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/25
 */
@Data
public class CollectListResult {

    @JSONField(name = "class")
    private List<CollectClass> collectClasses;

    private List<CollectDetail> list;

    private Integer code;

    private String limit;

    private String msg;

    private Integer page;

    private Integer pagecount;

    private Integer total;

}
