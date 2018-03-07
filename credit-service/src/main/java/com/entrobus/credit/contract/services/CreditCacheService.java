package com.entrobus.credit.contract.services;

import com.entrobus.credit.vo.base.BsStaticVo;
import com.entrobus.credit.vo.user.CacheUserInfo;

public interface CreditCacheService {

    CacheUserInfo getUserCacheByUid(String userId);

    CacheUserInfo getUserCacheBySid(String sid);
    /**
     * 翻译
     * 自行拼装key值
     * @return
     */
    String translate(String key);


    <T> BsStaticVo getBsStatic(String codeType, T codeValue);

    /**
     * 翻译
     * 实际查询静态数据缓存codeName
     * @param type 静态数据codeType值
     * @param value 静态数据codeValue值
     * @return
     */
    <T> String translate(String type, T value);

}
