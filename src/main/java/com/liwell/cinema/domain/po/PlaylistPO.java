package com.liwell.cinema.domain.po;

import com.liwell.cinema.domain.entity.Playlist;
import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/31
 */
@Data
public class PlaylistPO extends Playlist {

    private String sourceName;

    private String player;

}
