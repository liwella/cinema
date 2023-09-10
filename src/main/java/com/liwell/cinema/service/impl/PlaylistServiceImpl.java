package com.liwell.cinema.service.impl;

import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.PlaylistGetDTO;
import com.liwell.cinema.domain.entity.Playlist;
import com.liwell.cinema.domain.po.PlaylistPO;
import com.liwell.cinema.domain.vo.PlayDetailVO;
import com.liwell.cinema.domain.vo.PlayTypeVO;
import com.liwell.cinema.domain.vo.PlaylistVO;
import com.liwell.cinema.mapper.PlaylistMapper;
import com.liwell.cinema.service.PlaylistService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<PlaylistPO> playlistPOS = baseMapper.selectPlaylistPO(dto.getMovieId());
        List<PlaylistVO> result = new ArrayList<>();
        for (PlaylistPO playlistPO : playlistPOS) {
            if (StringUtils.isBlank(playlistPO.getSeparatorNote())) {
                playlistPO.setSeparatorNote("$$$");
            }
            String separatorNote = ReUtil.escape(playlistPO.getSeparatorNote());
            String[] playTypes = playlistPO.getPlayType().split(separatorNote);
            String[] playSources = playlistPO.getPlayUrl().split(separatorNote);
            List<PlayTypeVO> playTypeVOS = new ArrayList<>();
            for (int i = 0; i < playSources.length; i++) {
                String playSource = playSources[i];
                String[] playDetails = playSource.split("#");
                List<PlayDetailVO> playDetailVOS = new ArrayList<>();
                for (String playDetail : playDetails) {
                    PlayDetailVO playDetailVO = new PlayDetailVO();
                    if (playDetail.contains("$")) {
                        String[] tagAndUrl = playDetail.split("\\$");
                        playDetailVO.setPlayTag(tagAndUrl[0]);
                        playDetailVO.setPlayUrl(tagAndUrl[1]);
                    } else {
                        playDetailVO.setPlayTag("HD");
                        playDetailVO.setPlayUrl(playDetail);
                    }
                    playDetailVOS.add(playDetailVO);
                }
                PlayTypeVO playTypeVO = new PlayTypeVO();
                playTypeVO.setPlayType(playTypes[i]);
                playTypeVO.setPlayer(playlistPO.getPlayer());
                playTypeVO.setPlayDetailList(playDetailVOS);
                playTypeVOS.add(playTypeVO);
            }
            PlaylistVO playlistVO = new PlaylistVO();
            playlistVO.setSourceName(playlistPO.getSourceName());
            playlistVO.setMovieId(dto.getMovieId());
            playlistVO.setPlaySources(playTypeVOS);
            result.add(playlistVO);
        }
        return result;
    }

}
