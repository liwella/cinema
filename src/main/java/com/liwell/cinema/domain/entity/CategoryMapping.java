package com.liwell.cinema.domain.entity;

import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/30
 */
@Data
public class CategoryMapping {

    private Integer id;

    private Integer categoryId;

    private String categoryName;

    private Integer sourceId;

    private Integer sourceTypeId;

}
