package com.entrobus.credit.user.services;


import com.entrobus.credit.vo.user.UserInfoCache;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface UserCacheService {

    UserInfoCache getUserCache(String userId);

    boolean setUserCache(UserInfoCache userCache);

    boolean removeUserCache(String userId);
}
