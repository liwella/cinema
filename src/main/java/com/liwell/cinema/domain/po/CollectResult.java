package com.liwell.cinema.domain.po;

import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/26
 */
@Data
public class CollectResult {

    private Integer code;

    private String limit;

    private String msg;

    private Integer page;

    private Integer pagecount;

    private Integer total;

}
