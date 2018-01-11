package com.entrobus.credit.wechat.service.impl;

import com.entrobus.credit.pojo.wechat.WechatUser;
import com.entrobus.credit.pojo.wechat.WechatUserExample;
import com.entrobus.credit.wechat.dao.WechatUserMapper;
import com.entrobus.credit.wechat.service.WechatUserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatUserServiceImpl implements WechatUserService {
    @Autowired
    private WechatUserMapper wechatUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(WechatUserServiceImpl.class);

    public int countByExample(WechatUserExample example) {
        int count = this.wechatUserMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public WechatUser selectByPrimaryKey(String id) {
        return this.wechatUserMapper.selectByPrimaryKey(id);
    }

    public List<WechatUser> selectByExample(WechatUserExample example) {
        return this.wechatUserMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.wechatUserMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WechatUser record) {
        return this.wechatUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WechatUser record) {
        return this.wechatUserMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(WechatUserExample example) {
        return this.wechatUserMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(WechatUser record, WechatUserExample example) {
        return this.wechatUserMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(WechatUser record, WechatUserExample example) {
        return this.wechatUserMapper.updateByExample(record, example);
    }

    public int insert(WechatUser record) {
        return this.wechatUserMapper.insert(record);
    }

    public int insertSelective(WechatUser record) {
        return this.wechatUserMapper.insertSelective(record);
    }
}