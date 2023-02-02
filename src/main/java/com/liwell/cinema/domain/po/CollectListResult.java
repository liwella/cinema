package com.liwell.cinema.domain.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/25
 */
@Data
public class CollectListResult extends CollectResult {

    @JsonProperty("class")
    private List<CollectCategory> collectCategories;

    private List<CollectDetail> list;

}
