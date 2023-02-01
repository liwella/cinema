package com.liwell.cinema.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.entity.MovieClass;
import com.liwell.cinema.domain.vo.MovieClassListVO;
import com.liwell.cinema.mapper.MovieClassMapper;
import com.liwell.cinema.service.MovieClassService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/01
 */
@Service
public class MovieClassServiceImpl extends ServiceImpl<MovieClassMapper, MovieClass> implements MovieClassService {

    @Override
    public List<MovieClassListVO> listMovieClass(Integer parent) {
        Map<String, Integer> params = new HashMap<>();
        params.put("parent", parent);
        List<MovieClass> movieClasses = baseMapper.selectList(
                new QueryWrapper<MovieClass>().allEq(params).orderByAsc("sort"));
        if (CollectionUtil.isEmpty(movieClasses)) {
            return new ArrayList<>();
        }
        List<MovieClassListVO> result = BeanUtil.copyToList(movieClasses, MovieClassListVO.class);
        for (MovieClassListVO movieClassListVO : result) {
            movieClassListVO.setChildren(listMovieClass(movieClassListVO.getId()));
        }
        return result;
    }

}
