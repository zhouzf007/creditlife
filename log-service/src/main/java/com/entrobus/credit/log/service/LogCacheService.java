package com.entrobus.credit.log.service;

public interface LogCacheService {
    <T> String translation(String prefix, T value);
}
