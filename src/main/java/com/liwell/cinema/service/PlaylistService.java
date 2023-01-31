package com.liwell.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liwell.cinema.domain.dto.PlaylistGetDTO;
import com.liwell.cinema.domain.entity.Playlist;
import com.liwell.cinema.domain.vo.PlaylistVO;

import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/01/31
 */
public interface PlaylistService extends IService<Playlist> {

    List<PlaylistVO> getPlaylist(PlaylistGetDTO dto);

}
