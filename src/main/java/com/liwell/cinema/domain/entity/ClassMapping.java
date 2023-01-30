package com.liwell.cinema.domain.entity;

import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/30
 */
@Data
public class ClassMapping {

    private Integer id;

    private Integer movieClassId;

    private String movieClassName;

    private Integer sourceId;

    private Integer sourceTypeId;

}
