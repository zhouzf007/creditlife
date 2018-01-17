package com.entrobus.credit.user.services.impl;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.pojo.user.UserAccount;
import com.entrobus.credit.pojo.user.UserAccountExample;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.pojo.user.UserInfoExample;
import com.entrobus.credit.vo.user.CacheUserInfo;
import com.entrobus.credit.vo.user.UserAccountInfo;
import com.entrobus.credit.user.dao.UserInfoMapper;
import com.entrobus.credit.user.services.UserAccountService;
import com.entrobus.credit.user.services.UserInfoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import utils.ShiroUtils;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private RedisTemplate redisTemplate;

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

    @Override
    public CacheUserInfo getLoginUserInfo(UserInfo record, String token) {
        CacheUserInfo loginUserInfo = new CacheUserInfo();
        try {
            BeanUtils.copyProperties(loginUserInfo, record);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        UserAccountExample userAccountExample = new UserAccountExample();
        userAccountExample.createCriteria().andIdEqualTo(record.getAccountId());
        List<UserAccount> userAccounts = userAccountService.selectByExample(userAccountExample);
        List<UserAccountInfo> userAccountInfos = new ArrayList<>();
        for (UserAccount userAccount : userAccounts) {
            UserAccountInfo userAccountInfo = new UserAccountInfo();
            try {
                BeanUtils.copyProperties(userAccountInfo, userAccount);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            userAccountInfos.add(userAccountInfo);
        }
        loginUserInfo.setUserAccountInfos(userAccountInfos);
        CacheService.setString(redisTemplate, Cachekey.User.SID_PREFIX+ token, loginUserInfo.getId());
        CacheService.setString(redisTemplate, Cachekey.User.UID_SID_PREFIX+ loginUserInfo.getId(), token);
        CacheService.setCacheObj(redisTemplate, Cachekey.User.UID_PREFIX+ loginUserInfo.getId(), loginUserInfo);
        return loginUserInfo;
    }

    @Override
    public int addUserInfo(UserInfo record) {
        record.setId(GUIDUtil.genRandomGUID());
        record.setCreateTime(new Date());
        //生成20位长度的随机数，用作密码加密的盐
        String salt = RandomStringUtils.randomAlphanumeric(20);
        //将密码使用sha256加密
        record.setPwd(ShiroUtils.sha256(record.getPwd(), salt));
        record.setSalt(salt);
        record.setState(Constants.USER_STATUS.NORMAL);
        record.setDeleteFlag(Constants.DELETE_FLAG.NO);
        return userInfoMapper.insertSelective(record);
    }
}