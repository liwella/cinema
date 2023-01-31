package com.liwell.cinema.controller;

import com.liwell.cinema.domain.dto.PlaylistGetDTO;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.domain.vo.PlaylistVO;
import com.liwell.cinema.service.PlaylistService;
import com.liwell.cinema.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/01/31
 */
@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/getPlaylist")
    public Result<List<PlaylistVO>> getPlaylist(@RequestBody @Valid PlaylistGetDTO dto) {
        return ResultUtil.success(playlistService.getPlaylist(dto));
    }

}
