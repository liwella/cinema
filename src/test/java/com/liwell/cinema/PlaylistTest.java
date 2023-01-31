package com.liwell.cinema;

import com.liwell.cinema.domain.dto.PlaylistGetDTO;
import com.liwell.cinema.domain.vo.PlaylistVO;
import com.liwell.cinema.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/31
 */
@SpringBootTest
@ActiveProfiles("local")
public class PlaylistTest {

    @Autowired
    private PlaylistService playlistService;

    @Test
    public void testList() {
        PlaylistGetDTO playlistGetDTO = new PlaylistGetDTO();
        playlistGetDTO.setMovieId(82);
        List<PlaylistVO> playlist = playlistService.getPlaylist(playlistGetDTO);
        System.out.println(playlist);
    }

}
