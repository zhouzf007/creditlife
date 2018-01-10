package com.entrobus.credit.wechat.service.impl;

import com.entrobus.credit.pojo.wechat.WechatPublicAccount;
import com.entrobus.credit.pojo.wechat.WechatPublicAccountExample;
import com.entrobus.credit.wechat.dao.WechatPublicAccountMapper;
import com.entrobus.credit.wechat.service.WechatPublicAccountService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatPublicAccountServiceImpl implements WechatPublicAccountService {
    @Autowired
    private WechatPublicAccountMapper wechatPublicAccountMapper;

    private static final Logger logger = LoggerFactory.getLogger(WechatPublicAccountServiceImpl.class);

    public int countByExample(WechatPublicAccountExample example) {
        int count = this.wechatPublicAccountMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public WechatPublicAccount selectByPrimaryKey(String id) {
        return this.wechatPublicAccountMapper.selectByPrimaryKey(id);
    }

    public List<WechatPublicAccount> selectByExample(WechatPublicAccountExample example) {
        return this.wechatPublicAccountMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.wechatPublicAccountMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WechatPublicAccount record) {
        return this.wechatPublicAccountMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WechatPublicAccount record) {
        return this.wechatPublicAccountMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(WechatPublicAccountExample example) {
        return this.wechatPublicAccountMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(WechatPublicAccount record, WechatPublicAccountExample example) {
        return this.wechatPublicAccountMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(WechatPublicAccount record, WechatPublicAccountExample example) {
        return this.wechatPublicAccountMapper.updateByExample(record, example);
    }

    public int insert(WechatPublicAccount record) {
        return this.wechatPublicAccountMapper.insert(record);
    }

    public int insertSelective(WechatPublicAccount record) {
        return this.wechatPublicAccountMapper.insertSelective(record);
    }
}