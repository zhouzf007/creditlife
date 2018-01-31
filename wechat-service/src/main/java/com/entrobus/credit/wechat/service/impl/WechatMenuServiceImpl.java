package com.entrobus.credit.wechat.service.impl;

import com.entrobus.credit.pojo.wechat.WechatMenu;
import com.entrobus.credit.pojo.wechat.WechatMenuExample;
import com.entrobus.credit.wechat.dao.WechatMenuMapper;
import com.entrobus.credit.wechat.service.WechatMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WechatMenuServiceImpl implements WechatMenuService {
    @Autowired
    private WechatMenuMapper wechatMenuMapper;

    private static final Logger logger = LoggerFactory.getLogger(WechatMenuServiceImpl.class);

    public int countByExample(WechatMenuExample example) {
        int count = this.wechatMenuMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public WechatMenu selectByPrimaryKey(String id) {
        return this.wechatMenuMapper.selectByPrimaryKey(id);
    }

    public List<WechatMenu> selectByExample(WechatMenuExample example) {
        return this.wechatMenuMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.wechatMenuMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WechatMenu record) {
        return this.wechatMenuMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WechatMenu record) {
        return this.wechatMenuMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(WechatMenuExample example) {
        return this.wechatMenuMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(WechatMenu record, WechatMenuExample example) {
        return this.wechatMenuMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(WechatMenu record, WechatMenuExample example) {
        return this.wechatMenuMapper.updateByExample(record, example);
    }

    public int insert(WechatMenu record) {
        return this.wechatMenuMapper.insert(record);
    }

    public int insertSelective(WechatMenu record) {
        return this.wechatMenuMapper.insertSelective(record);
    }
}