package com.entrobus.credit.user.services;


import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.vo.user.CacheUserInfo;
import com.entrobus.credit.vo.user.UserInfoCache;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface UserCacheService {

    UserInfoCache getUserCache(String userId);

    boolean setUserCache(UserInfoCache userCache);

    boolean removeUserCache(String userId);

    CacheUserInfo getUserCacheByUid(String userId);

    CacheUserInfo getUserCacheBySid(String sid);

    String translate(String key);
}
