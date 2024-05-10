package com.liwell.cinema.domain.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2024/5/9
 */
@Data
public class MenuChildPageDTO extends Page {

    private Integer parentId;

}
