package com.liwell.cinema.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/03
 */
@Data
public class CollectTaskAddDTO {

    private Integer duration;

    private List<Integer> sourceIds;

}
