package com.entrobus.credit.user.services;


import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.vo.base.BsStaticVo;
import com.entrobus.credit.vo.user.CacheUserInfo;
import com.entrobus.credit.vo.user.UserInfoCache;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface UserCacheService {

    /**
     * 翻译
     * 实际查询静态数据缓存codeName
     * @param type 静态数据codeType值
     * @param value 静态数据codeValue值
     * @return
     */
    <T> String translate(String type, T value);

    <T> BsStaticVo getBsStatic(String codeType, T codeValue);

    UserInfoCache getUserCache(String userId);

    boolean setUserCache(UserInfoCache userCache);

    boolean removeUserCache(String userId);

    CacheUserInfo getUserCacheByUid(String userId);

    CacheUserInfo getUserCacheBySid(String sid);

    String translate(String key);

    void refreshUserCache(String id);

    void refreshUserCache(UserInfo record);
}
