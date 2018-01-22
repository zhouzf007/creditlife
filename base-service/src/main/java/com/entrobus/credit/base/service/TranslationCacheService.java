package com.entrobus.credit.base.service;

/**
 * 翻译缓存
 */
public interface TranslationCacheService {
    /**
     * 初始化翻译缓存
     */
    void init();

    <T> void addTranslation(String prefix, T value, String name);
}
