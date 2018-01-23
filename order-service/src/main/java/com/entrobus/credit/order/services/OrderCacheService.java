package com.entrobus.credit.order.services;

import com.entrobus.credit.vo.base.BsStaticVo;
import com.entrobus.credit.vo.user.CacheUserInfo;

public interface OrderCacheService {

    CacheUserInfo getUserCacheByUid(String userId);

    CacheUserInfo getUserCacheBySid(String sid);

    String translate(String key);


    <T> BsStaticVo getBsStatic(String codeType, T codeValue);


    <T> String getCodeName(String codeType, T codeValue);

    String getOrderApplyNo();
}
