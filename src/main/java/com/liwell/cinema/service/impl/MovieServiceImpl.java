package com.liwell.cinema.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.MvCollectDTO;
import com.liwell.cinema.domain.entity.CollectConfig;
import com.liwell.cinema.domain.entity.Movie;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.CollectConfigMapper;
import com.liwell.cinema.mapper.MovieMapper;
import com.liwell.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    private CollectConfigMapper collectConfigMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void collect(MvCollectDTO mvCollectDTO) {
        CollectConfig collectConfig = collectConfigMapper.selectOne(
                new QueryWrapper<CollectConfig>().eq("id", mvCollectDTO.getCollectId()).eq("state", 1));
        if (collectConfig == null) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        String listUrl = collectConfig.getListUrl();
        HttpHeaders httpHeaders = new HttpHeaders();

    }

}
