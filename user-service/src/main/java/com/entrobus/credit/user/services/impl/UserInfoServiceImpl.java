package com.entrobus.credit.user.services.impl;

import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.pojo.user.UserInfoExample;
import com.entrobus.credit.user.dao.UserInfoMapper;
import com.entrobus.credit.user.services.UserInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    public int countByExample(UserInfoExample example) {
        int count = this.userInfoMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public UserInfo selectByPrimaryKey(String id) {
        return this.userInfoMapper.selectByPrimaryKey(id);
    }

    public List<UserInfo> selectByExample(UserInfoExample example) {
        return this.userInfoMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.userInfoMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserInfo record) {
        return this.userInfoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserInfo record) {
        return this.userInfoMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(UserInfoExample example) {
        return this.userInfoMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(UserInfo record, UserInfoExample example) {
        return this.userInfoMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(UserInfo record, UserInfoExample example) {
        return this.userInfoMapper.updateByExample(record, example);
    }

    public int insert(UserInfo record) {
        return this.userInfoMapper.insert(record);
    }

    public int insertSelective(UserInfo record) {
        return this.userInfoMapper.insertSelective(record);
    }
}