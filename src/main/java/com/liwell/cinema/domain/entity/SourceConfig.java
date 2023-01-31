package com.liwell.cinema.domain.entity;

import com.liwell.cinema.domain.enums.StateEnum;
import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Data
public class SourceConfig {

    private Integer id;

    private String listUrl;

    private String detailUrl;

    private StateEnum state;

}
