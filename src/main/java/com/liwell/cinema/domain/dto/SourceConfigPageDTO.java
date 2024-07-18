package com.liwell.cinema.domain.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.enums.StateEnum;
import lombok.Data;

/**
 * @author litianyi
 * @filename SourceConfigListDTO.java
 * @purpose
 * @history 2023/8/22 litianyi
 */
@Data
public class SourceConfigPageDTO extends Page {

    private StateEnum state;

}
