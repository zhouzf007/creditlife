package com.entrobus.credit.order.services;

import com.entrobus.credit.vo.base.BsStaticVo;
import com.entrobus.credit.vo.user.CacheUserInfo;

public interface OrderCacheService {

    CacheUserInfo getUserCacheByUid(String userId);

    CacheUserInfo getUserCacheBySid(String sid);

    String translate(String key);


    <T> BsStaticVo getBsStatic(String codeType, T codeValue);

    /**
     * 翻译
     * 实际查询静态数据缓存
     * @param type
     * @param value
     * @return
     */
    <T> String translate(String type, T value);

    String getOrderApplyNo();
}
