package com.entrobus.credit.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by zhouzf on 2017/12/27.
 */
public class RedisUtil {

    public static void setString(RedisTemplate redisTemplate, String k, String v) {
        redisTemplate.opsForValue().set(k, v);
    }
}
