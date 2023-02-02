package com.liwell.cinema.domain.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/25
 */
@Data
public class CollectCategory {

    @JsonProperty("type_id")
    private Integer typeId;

    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("type_pid")
    private Integer typePid;

}
