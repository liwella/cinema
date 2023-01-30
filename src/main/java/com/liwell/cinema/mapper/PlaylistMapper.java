package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.entity.Playlist;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/29
 */
@Mapper
@Repository
public interface PlaylistMapper extends BaseMapper<Playlist> {

    void insertPlaylist(List<Playlist> playlists);

}
