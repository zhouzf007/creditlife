package com.entrobus.credit.wechat.service;

import com.entrobus.credit.pojo.wechat.WechatReqMsgLog;
import com.entrobus.credit.pojo.wechat.WechatReqMsgLogExample;

import java.util.List;

public interface WechatReqMsgLogService {
    int countByExample(WechatReqMsgLogExample example);

    WechatReqMsgLog selectByPrimaryKey(String id);

    List<WechatReqMsgLog> selectByExample(WechatReqMsgLogExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WechatReqMsgLog record);

    int updateByPrimaryKey(WechatReqMsgLog record);

    int deleteByExample(WechatReqMsgLogExample example);

    int updateByExampleSelective(WechatReqMsgLog record, WechatReqMsgLogExample example);

    int updateByExample(WechatReqMsgLog record, WechatReqMsgLogExample example);

    int insert(WechatReqMsgLog record);

    int insertSelective(WechatReqMsgLog record);
}