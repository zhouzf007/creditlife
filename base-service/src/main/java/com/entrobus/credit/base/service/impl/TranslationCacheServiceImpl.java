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
        addTranslation(Cachekey.Translation.ORDER_STATE , Constants.ORDER_STATE.NOT_LOAN, "未申请");
        addTranslation(Cachekey.Translation.ORDER_STATE , Constants.ORDER_STATE.AUIDT_PENGDING, "待审核");
        addTranslation(Cachekey.Translation.ORDER_STATE , Constants.ORDER_STATE.LOAN_PENGDING, "待放款");
        addTranslation(Cachekey.Translation.ORDER_STATE , Constants.ORDER_STATE.REJECTION, "已驳回");
        addTranslation(Cachekey.Translation.ORDER_STATE , Constants.ORDER_STATE.PASS, "使用中");
        addTranslation(Cachekey.Translation.ORDER_STATE , Constants.ORDER_STATE.OVERDUE, "已逾期");
        addTranslation(Cachekey.Translation.ORDER_STATE , Constants.ORDER_STATE.FINISHED, "已完成");

        addTranslation(Cachekey.Translation.REPAYMENT_STATE , Constants.REPAYMENT_ORDER_STATE.PASS, "使用中");
        addTranslation(Cachekey.Translation.REPAYMENT_STATE , Constants.REPAYMENT_ORDER_STATE.OVERDUE, "已逾期");
        addTranslation(Cachekey.Translation.REPAYMENT_STATE , Constants.REPAYMENT_ORDER_STATE.FINISHED, "已结清");

        //操作状态
        addTranslation(Cachekey.Translation.LOG_OPERATION_STATE , Constants.OPERATION_STATE.FAIL, "操作失败");
        addTranslation(Cachekey.Translation.LOG_OPERATION_STATE , Constants.OPERATION_STATE.ERROR, "异常");
        addTranslation(Cachekey.Translation.LOG_OPERATION_STATE , Constants.OPERATION_STATE.ERROR, "操作成功");

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
