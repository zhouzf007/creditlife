package com.entrobus.credit.wechat.service.impl;

import com.entrobus.credit.pojo.wechat.WechatReqMsgLog;
import com.entrobus.credit.pojo.wechat.WechatReqMsgLogExample;
import com.entrobus.credit.wechat.dao.WechatReqMsgLogMapper;
import com.entrobus.credit.wechat.service.WechatReqMsgLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WechatReqMsgLogServiceImpl implements WechatReqMsgLogService {
    @Autowired
    private WechatReqMsgLogMapper wechatReqMsgLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(WechatReqMsgLogServiceImpl.class);

    public int countByExample(WechatReqMsgLogExample example) {
        int count = this.wechatReqMsgLogMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public WechatReqMsgLog selectByPrimaryKey(String id) {
        return this.wechatReqMsgLogMapper.selectByPrimaryKey(id);
    }

    public List<WechatReqMsgLog> selectByExample(WechatReqMsgLogExample example) {
        return this.wechatReqMsgLogMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.wechatReqMsgLogMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WechatReqMsgLog record) {
        return this.wechatReqMsgLogMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WechatReqMsgLog record) {
        return this.wechatReqMsgLogMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(WechatReqMsgLogExample example) {
        return this.wechatReqMsgLogMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(WechatReqMsgLog record, WechatReqMsgLogExample example) {
        return this.wechatReqMsgLogMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(WechatReqMsgLog record, WechatReqMsgLogExample example) {
        return this.wechatReqMsgLogMapper.updateByExample(record, example);
    }

    public int insert(WechatReqMsgLog record) {
        return this.wechatReqMsgLogMapper.insert(record);
    }

    public int insertSelective(WechatReqMsgLog record) {
        return this.wechatReqMsgLogMapper.insertSelective(record);
    }
}