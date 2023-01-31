package com.liwell.cinema.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/01/31
 */
@Data
public class PlaylistGetDTO {

    @NotNull(message = "影片id不能为空")
    private Integer movieId;

}
