package com.liwell.cinema.domain.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.liwell.cinema.helper.EnumDeserializer;

/**
 * Description:
 *
 * @author: litianyi
 * @date: Created on 2023/02/02
 */
@JsonDeserialize(using = EnumDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface BaseEnum extends IEnum {



}
