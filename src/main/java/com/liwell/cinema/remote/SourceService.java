package com.liwell.cinema.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liwell.cinema.domain.entity.SourceConfig;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.CollectDetailResult;
import com.liwell.cinema.domain.po.CollectListResult;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.SourceConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/2/2
 */
@Component
public class SourceService {

    @Autowired
    private SourceConfigMapper sourceConfigMapper;
    @Autowired
    private RestTemplate trustedTemplate;

    /**
     * 获取分类等信息
     * @param sourceId
     * @return
     */
    public CollectListResult sourceBaseInfo(Integer sourceId) {
        SourceConfig sourceConfig = getSourceConfig(sourceId);
        ResponseEntity<CollectListResult> pageCountResponse = trustedTemplate
                .getForEntity(sourceConfig.getListUrl(), CollectListResult.class, new HashMap<>());
        if (pageCountResponse.getBody() == null || pageCountResponse.getStatusCodeValue() != 200) {
            throw new ResultException(ResultEnum.THIRD_INTERFACE_ERROR);
        }
        return pageCountResponse.getBody();
    }

    /**
     * 分页采集资源
     * @param url
     * @param page
     * @return
     */
    public CollectDetailResult pageSource(String url, Integer page) {
        Map<String, Integer> detailParam = new HashMap<>();
        detailParam.put("pg", page);
        ResponseEntity<CollectDetailResult> detailResultResponseEntity = trustedTemplate
                .getForEntity(url + "&pg={pg}", CollectDetailResult.class, detailParam);
        return detailResultResponseEntity.getBody();
    }

    /**
     * 获取采集配置
     * @param sourceId
     * @return
     */
    public SourceConfig getSourceConfig(Integer sourceId) {
        SourceConfig sourceConfig = sourceConfigMapper.selectOne(
                new QueryWrapper<SourceConfig>().eq("id", sourceId).eq("state", 1));
        if (sourceConfig == null) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        return sourceConfig;
    }

}
