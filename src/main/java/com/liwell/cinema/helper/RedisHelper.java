package com.liwell.cinema.helper;

import com.liwell.cinema.domain.constants.RedisConstants;
import com.liwell.cinema.domain.enums.BaseEnum;
import com.liwell.cinema.domain.po.LoginUser;
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
        Integer value = (Integer) valueOperations.get(RedisConstants.COLLECT_STATE_KEY + taskId);
        return EnumUtils.get(enumClass, value);
    }

    public void setLoginUser(Integer userId, LoginUser loginUser) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(RedisConstants.LOGIN_KEY + userId, loginUser);
    }

    public LoginUser getLoginUser(Integer userId) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(RedisConstants.LOGIN_KEY + userId);
        if (Objects.isNull(o)) {
            return null;
        }
        return (LoginUser) o;
    }

    public void removeLoginUser(Integer userId) {
        redisTemplate.delete(RedisConstants.LOGIN_KEY + userId);
    }
}
