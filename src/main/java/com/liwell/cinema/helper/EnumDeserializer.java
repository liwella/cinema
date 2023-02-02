package com.liwell.cinema.helper;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.liwell.cinema.domain.enums.BaseEnum;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.util.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/2/2
 */
public class EnumDeserializer extends JsonDeserializer<BaseEnum> {

    @Override
    public BaseEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        // 当前处理的字段值对象
        JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        // 当前处理的字段名
        String currentName = jsonParser.getCurrentName();
        // 参数对象
        Object currentValue = jsonParser.getCurrentValue();
        // 根据参数对象和字段名获取字段的类型
        Class findPropertyType = BeanUtils.findPropertyType(currentName, currentValue.getClass());
        String text = treeNode.asText();
        if (StringUtils.isBlank(text)) {
            text = treeNode.get("value").asText();
        }
        BaseEnum result = null;
        if (StringUtils.isNotBlank(text)) {
            result = EnumUtils.get(findPropertyType, new Integer(text));
        }
        if (result == null) {
            throw new ResultException(ResultEnum.PARAMETER_ERROR);
        }
        return result;
    }

}
