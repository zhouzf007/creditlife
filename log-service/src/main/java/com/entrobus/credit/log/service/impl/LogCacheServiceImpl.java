package com.entrobus.credit.log.service.impl;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.log.service.LogCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogCacheServiceImpl implements LogCacheService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public<T> String translation(String prefix, T value){
        return CacheService.getString(redisTemplate,getKey(prefix,value));
    }
    private <T> String getKey(String prefix, T value) {
        return prefix + value;
    }
}
