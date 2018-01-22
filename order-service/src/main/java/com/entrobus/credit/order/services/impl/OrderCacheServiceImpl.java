package com.entrobus.credit.order.services.impl;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.order.services.OrderCacheService;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderCacheServiceImpl implements OrderCacheService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public CacheUserInfo getUserCacheByUid(String userId) {
        Object o = CacheService.getObject(redisTemplate, Cachekey.User.UID_PREFIX + userId);
        return o instanceof CacheUserInfo ? (CacheUserInfo) o : null;
    }

    @Override
    public CacheUserInfo getUserCacheBySid(String sid) {
        String uid = CacheService.getString(redisTemplate, Cachekey.User.SID_PREFIX + sid);
        Object o = CacheService.getObject(redisTemplate, Cachekey.User.UID_PREFIX + uid);
        return o instanceof CacheUserInfo ? (CacheUserInfo) o : null;
    }

    @Override
    public String translate(String key) {
        return CacheService.getString(redisTemplate, key);
    }

    @Override
    public String getOrderApplyNo() {
        String datetime=DateUtils.formatDate(new Date(),"yyyyMMddHHmmss");
        long no=CacheService.getIncreaseNo(redisTemplate, Cachekey.Order.apply_no,1);
        return datetime+no;
    }

}
