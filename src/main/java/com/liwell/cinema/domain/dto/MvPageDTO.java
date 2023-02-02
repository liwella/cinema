package com.liwell.cinema.domain.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.enums.MvAreaEnum;
import lombok.Data;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@Data
public class MvPageDTO extends Page {

    private String mvName;

    private Integer mvType;

    private MvAreaEnum mvArea;

    private Integer mvYear;

}
