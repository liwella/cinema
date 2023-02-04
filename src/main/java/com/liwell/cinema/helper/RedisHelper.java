package com.liwell.cinema.helper;

import com.liwell.cinema.domain.constants.RedisConstants;
import com.liwell.cinema.domain.enums.BaseEnum;
import com.liwell.cinema.util.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

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
        Integer value = (Integer) valueOperations.get(RedisConstants.COLLECT_STATE_KEY + taskId);
        return EnumUtils.get(enumClass, value);
    }

}
