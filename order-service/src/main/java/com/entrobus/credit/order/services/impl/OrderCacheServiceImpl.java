package com.entrobus.credit.order.services.impl;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.order.services.OrderCacheService;
import com.entrobus.credit.vo.base.BsStaticVo;
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

    /**
     *  根据codeType和codeValue查找
     * @param codeType
     * @param codeValue
     * @return
     */
    @Override
    public<T> BsStaticVo getBsStatic(String codeType, T codeValue) {
        String key = Cachekey.BsStatics.TYPE_VALUE_ID + codeType + codeValue;
        String id = CacheService.getString(redisTemplate,key);
        if (id == null) return null;
        String idKey = Cachekey.BsStatics.ID_OBJ + id ;
        BsStaticVo cacheObj = CacheService.getCacheObj(redisTemplate, key, BsStaticVo.class);
        return cacheObj;
    }
    /**
     * 翻译
     * 实际查询静态数据缓存
     * @param type
     * @param value
     * @return
     */
    @Override
    public<T> String translate(String type, T value) {
        String key = Cachekey.BsStatics.TYPE_VALUE_NAME + type + value;
        return CacheService.getString(redisTemplate,key);
    }
    @Override
    public String getOrderApplyNo() {
        String datetime=DateUtils.formatDate(new Date(),"yyyyMMddHHmmss");
        long no=CacheService.getIncreaseNo(redisTemplate, Cachekey.Order.apply_no,1);
        return datetime+no;
    }

}
