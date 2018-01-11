package com.entrobus.credit.wechat.service;

import com.entrobus.credit.pojo.wechat.WechatPublicAccount;
import com.entrobus.credit.pojo.wechat.WechatPublicAccountExample;
import java.util.List;

public interface WechatPublicAccountService {
    int countByExample(WechatPublicAccountExample example);

    WechatPublicAccount selectByPrimaryKey(String id);

    List<WechatPublicAccount> selectByExample(WechatPublicAccountExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WechatPublicAccount record);

    int updateByPrimaryKey(WechatPublicAccount record);

    int deleteByExample(WechatPublicAccountExample example);

    int updateByExampleSelective(WechatPublicAccount record, WechatPublicAccountExample example);

    int updateByExample(WechatPublicAccount record, WechatPublicAccountExample example);

    int insert(WechatPublicAccount record);

    int insertSelective(WechatPublicAccount record);
}