package com.entrobus.credit.wechat.service;

import com.entrobus.credit.pojo.wechat.WechatMenu;
import com.entrobus.credit.pojo.wechat.WechatMenuExample;

import java.util.List;

public interface WechatMenuService {
    int countByExample(WechatMenuExample example);

    WechatMenu selectByPrimaryKey(String id);

    List<WechatMenu> selectByExample(WechatMenuExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WechatMenu record);

    int updateByPrimaryKey(WechatMenu record);

    int deleteByExample(WechatMenuExample example);

    int updateByExampleSelective(WechatMenu record, WechatMenuExample example);

    int updateByExample(WechatMenu record, WechatMenuExample example);

    int insert(WechatMenu record);

    int insertSelective(WechatMenu record);
}