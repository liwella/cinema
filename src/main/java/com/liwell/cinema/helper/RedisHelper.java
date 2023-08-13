package com.liwell.cinema.helper;

import com.liwell.cinema.domain.constants.RedisConstants;
import com.liwell.cinema.domain.enums.BaseEnum;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.util.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/2/4
 */
@Component
public class RedisHelper {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public <T extends BaseEnum> T getCollectTaskState(Class<T> enumClass, Integer taskId) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object taskState = valueOperations.get(RedisConstants.COLLECT_STATE_KEY + taskId);
        if (Objects.isNull(taskState)) {
            throw new ResultException(ResultEnum.TASK_NOT_EXISTS);
        }
        Integer value = (Integer) taskState;
        return EnumUtils.get(enumClass, value);
    }

}
