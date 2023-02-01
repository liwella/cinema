package com.liwell.cinema.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liwell.cinema.domain.dto.MovieClassAddDTO;
import com.liwell.cinema.domain.dto.MovieClassMoveDTO;
import com.liwell.cinema.domain.entity.MovieClass;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.vo.MovieClassListVO;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.mapper.MovieClassMapper;
import com.liwell.cinema.service.MovieClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Boolean addMovieClass(MovieClassAddDTO dto) {
        Integer sort;
        int level;
        Integer parentNumber = null;
        if (dto.getParent() == null) {
            level = 0;
            sort = baseMapper.getMaxSort(parentNumber);
        } else {
            MovieClass parent = baseMapper.selectOne(new QueryWrapper<MovieClass>().eq("id", dto.getParent()));
            level = parent.getLevel() + 1;
            sort = baseMapper.getMaxSort(dto.getParent());
            parentNumber = dto.getParent();
        }
        MovieClass movieClass = new MovieClass();
        movieClass.setName(dto.getName());
        if (sort == null) {
            sort = 0;
        }
        movieClass.setSort(++sort);
        movieClass.setParent(parentNumber);
        movieClass.setLevel(level);
        baseMapper.insert(movieClass);
        return true;
    }

    @Override
    @Transactional
    public Boolean moveMovieClass(MovieClassMoveDTO dto) {
        MovieClass movieClass = baseMapper.selectById(dto.getId());
        if (movieClass == null) {
            throw new ResultException(ResultEnum.DATA_NOT_EXIST);
        }
        Map<String, Integer> params = new HashMap<>();
        params.put("parent", movieClass.getParent());
        if (dto.getUp()) {
            if (movieClass.getSort() == 1) {
                throw new ResultException(ResultEnum.ALREADY_TOP);
            }
            params.put("sort", movieClass.getSort() - 1);
            MovieClass up = baseMapper.selectOne(new QueryWrapper<MovieClass>().allEq(params));
            transferPosition(up, movieClass);
        } else {
            if (movieClass.getSort().equals(baseMapper.getMaxSort(movieClass.getParent()))) {
                throw new ResultException(ResultEnum.ALREADY_TOP);
            }
            params.put("sort", movieClass.getSort() + 1);
            MovieClass down = baseMapper.selectOne(new QueryWrapper<MovieClass>().allEq(params));
            transferPosition(movieClass, down);
        }
        return true;
    }

    private void transferPosition(MovieClass up, MovieClass down) {
        baseMapper.update(null, new UpdateWrapper<MovieClass>()
                .set("sort", down.getSort() - 1).eq("id", down.getId()));
        baseMapper.update(null, new UpdateWrapper<MovieClass>()
                .set("sort", up.getSort() + 1).eq("id", up.getId()));
    }

}
