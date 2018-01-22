package com.entrobus.credit.order.services;

import com.entrobus.credit.vo.user.CacheUserInfo;

public interface OrderCacheService {

    CacheUserInfo getUserCacheByUid(String userId);

    CacheUserInfo getUserCacheBySid(String sid);

    String translate(String key);

    String getOrderApplyNo();
}
