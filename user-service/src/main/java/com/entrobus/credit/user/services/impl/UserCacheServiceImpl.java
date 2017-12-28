package com.entrobus.credit.user.services.impl;

import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.user.services.UserCacheService;
import com.entrobus.credit.vo.user.UserInfoCache;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by zhouzf on 2017/12/28.
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {

    private final static Logger logger = LoggerFactory.getLogger(UserCacheServiceImpl.class);

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public UserInfoCache getUserCache(String userId) {
        Object o = CacheService.getObject(redisTemplate, Cachekey.User.UID_PREFIX + userId);
        return o instanceof UserInfoCache ? (UserInfoCache) o : null;
    }

    @Override
    public boolean setUserCache(UserInfoCache userInfoCache) {
        if (userInfoCache == null) return false;
        CacheService.setCacheObj(redisTemplate, Cachekey.User.UID_PREFIX + userInfoCache.getId(), userInfoCache);
        return true;
    }

    @Override
    public boolean removeUserCache(String userId) {
        if (StringUtils.isEmpty(userId)) return false;
        CacheService.delete(redisTemplate, Cachekey.User.UID_PREFIX + userId);
        return true;
    }
}
