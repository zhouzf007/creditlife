package com.entrobus.credit.base.service.impl;

import com.entrobus.credit.base.service.TranslationCacheService;
import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 翻译
 * 程序启动完成后会自动刷新缓存
 */
@Service
public class TranslationCacheServiceImpl implements TranslationCacheService,CommandLineRunner {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 初始化翻译
     */
    @Override
    public void init() {
//         //已改为同步静态数据
//        addTranslation(Cachekey.Translation.REPAYMENT_TYPE , Constants.REPAYMENT_TYPE.INTEREST_CAPITAL, "先息后本");
//        addTranslation(Cachekey.Translation.REPAYMENT_TYPE , Constants.REPAYMENT_TYPE.MONTH_EQUAL, "等额本息");

    }

    public void  cache(String key,String value){
        CacheService.setString(redisTemplate, key, value);
    }
    @Override
    public<T> void addTranslation(String prefix, T value, String name){
        cache(getKey(prefix, value),name);
    }

    private <T> String getKey(String prefix, T value) {
        return prefix + value;
    }
    @Override
    public<T> String translation(String prefix, T value){
        return CacheService.getString(redisTemplate,getKey(prefix,value));
    }

    /**
     * 程序启动完成后执行
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        init();//初始化翻译
    }
}
