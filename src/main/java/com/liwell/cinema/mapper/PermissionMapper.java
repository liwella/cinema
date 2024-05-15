package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwell.cinema.domain.dto.ButtonPageDTO;
import com.liwell.cinema.domain.entity.Permission;
import com.liwell.cinema.domain.vo.PermissionListVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/29
 */
@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    List<PermissionListVO> listUserPermission(Integer userId);

    List<PermissionListVO> listMenu();

    Integer getMaxSort(Integer parentId);

    Page<PermissionListVO> pageButton(ButtonPageDTO dto);
}
