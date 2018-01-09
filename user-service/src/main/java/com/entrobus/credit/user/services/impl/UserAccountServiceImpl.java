package com.entrobus.credit.user.services.impl;

import com.entrobus.credit.pojo.user.UserAccount;
import com.entrobus.credit.pojo.user.UserAccountExample;
import com.entrobus.credit.user.dao.UserAccountMapper;
import com.entrobus.credit.user.services.UserAccountService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    public int countByExample(UserAccountExample example) {
        int count = this.userAccountMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public UserAccount selectByPrimaryKey(String id) {
        return this.userAccountMapper.selectByPrimaryKey(id);
    }

    public List<UserAccount> selectByExample(UserAccountExample example) {
        return this.userAccountMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.userAccountMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserAccount record) {
        return this.userAccountMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserAccount record) {
        return this.userAccountMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(UserAccountExample example) {
        return this.userAccountMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(UserAccount record, UserAccountExample example) {
        return this.userAccountMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(UserAccount record, UserAccountExample example) {
        return this.userAccountMapper.updateByExample(record, example);
    }

    public int insert(UserAccount record) {
        return this.userAccountMapper.insert(record);
    }

    public int insertSelective(UserAccount record) {
        return this.userAccountMapper.insertSelective(record);
    }
}