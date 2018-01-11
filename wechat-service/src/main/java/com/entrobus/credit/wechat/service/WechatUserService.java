package com.entrobus.credit.wechat.service;

import com.entrobus.credit.pojo.wechat.WechatUser;
import com.entrobus.credit.pojo.wechat.WechatUserExample;
import java.util.List;

public interface WechatUserService {
    int countByExample(WechatUserExample example);

    WechatUser selectByPrimaryKey(String id);

    List<WechatUser> selectByExample(WechatUserExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WechatUser record);

    int updateByPrimaryKey(WechatUser record);

    int deleteByExample(WechatUserExample example);

    int updateByExampleSelective(WechatUser record, WechatUserExample example);

    int updateByExample(WechatUser record, WechatUserExample example);

    int insert(WechatUser record);

    int insertSelective(WechatUser record);
}