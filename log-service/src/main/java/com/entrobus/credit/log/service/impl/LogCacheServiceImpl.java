package com.entrobus.credit.log.service.impl;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.log.service.LogCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogCacheServiceImpl implements LogCacheService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public<T> String translate(String type, T value){
        return CacheService.getString(redisTemplate, Cachekey.BsStatics.TYPE_VALUE_NAME + type + value);
    }
}
