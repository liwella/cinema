package com.liwell.cinema.domain.po;

import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/26
 */
@Data
public class CollectDetailResult extends CollectResult {

    private List<CollectDetail> list;

}
