package com.liwell.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liwell.cinema.domain.dto.RoleListDTO;
import com.liwell.cinema.domain.dto.RoleUpdateDTO;
import com.liwell.cinema.domain.entity.Role;
import com.liwell.cinema.domain.vo.RoleListVO;
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
public interface RoleMapper extends BaseMapper<Role> {

    List<RoleListVO> listRole(RoleListDTO dto);

    void updateRole(RoleUpdateDTO dto);
}
