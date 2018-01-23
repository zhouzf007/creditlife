package com.entrobus.credit.log.service;

public interface LogCacheService {
    <T> String translate(String type, T value);
}
