package com.liwell.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.PlaylistGetDTO;
import com.liwell.cinema.domain.entity.Playlist;
import com.liwell.cinema.domain.vo.PlaylistVO;
import com.liwell.cinema.mapper.PlaylistMapper;
import com.liwell.cinema.service.PlaylistService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/01/31
 */
@Service
public class PlaylistServiceImpl extends ServiceImpl<PlaylistMapper, Playlist> implements PlaylistService {

    @Override
    public List<PlaylistVO> getPlaylist(PlaylistGetDTO dto) {
        return null;
    }

}
