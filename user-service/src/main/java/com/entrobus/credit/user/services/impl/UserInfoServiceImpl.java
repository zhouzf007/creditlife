package com.entrobus.credit.user.services.impl;

import com.alibaba.fastjson.JSONArray;
import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.common.util.HttpClientUtil;
import com.entrobus.credit.pojo.user.UserAccount;
import com.entrobus.credit.pojo.user.UserAccountExample;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.pojo.user.UserInfoExample;
import com.entrobus.credit.user.client.BsStaticsClient;
import com.entrobus.credit.vo.user.CacheUserInfo;
import com.entrobus.credit.vo.user.UserAccountInfo;
import com.entrobus.credit.user.dao.UserInfoMapper;
import com.entrobus.credit.user.services.UserAccountService;
import com.entrobus.credit.user.services.UserInfoService;

import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.entrobus.credit.user.utils.ShiroUtils;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BsStaticsClient bsStaticsClient;

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
        if(loginUserInfo.getRole() == Constants.USER_ROLE.ORDINARY){
            loginUserInfo.setRoleName("普通用户");
        }else  if(loginUserInfo.getRole() == Constants.USER_ROLE.OWNER){
            loginUserInfo.setRoleName("业主");
        }
        List<UserAccountInfo> userAccountInfos = getUserAccountInfos(record, loginUserInfo);
        loginUserInfo.setUserAccountInfos(userAccountInfos);
        String oldToken = CacheService.getString(redisTemplate, Cachekey.User.UID_SID_PREFIX + loginUserInfo.getId());
        CacheService.delete(redisTemplate, Cachekey.User.SID_PREFIX + oldToken);
        CacheService.setString(redisTemplate, Cachekey.User.SID_PREFIX + token, loginUserInfo.getId());
        CacheService.setString(redisTemplate, Cachekey.User.UID_SID_PREFIX + loginUserInfo.getId(), token);
        CacheService.setCacheObj(redisTemplate, Cachekey.User.UID_PREFIX + loginUserInfo.getId(), loginUserInfo);
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
        record.setRole(Constants.USER_ROLE.ORDINARY);
        record.setDeleteFlag(Constants.DELETE_FLAG.NO);
        return userInfoMapper.insertSelective(record);
    }

    /**
     * 批量刷新用户缓存
     * @param userIds 如果是null，则刷新全部
     * @return
     */
    @Override
    public int initUserCache(List<String> userIds) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        if(userIds != null) {

            if (userIds.isEmpty()){
                return 0;
            }else {
                criteria.andIdIn(userIds);
            }
        }
        List<UserInfo> userInfoList = selectByExample(example);
        for (UserInfo userInfo : userInfoList) {
            initUserCache(userInfo);
        }
        return userInfoList.size();
    }

    @Override
    public CacheUserInfo initUserCache(UserInfo record) {
        CacheUserInfo cacheUserInfo = new CacheUserInfo();
        try {
            BeanUtils.copyProperties(cacheUserInfo, record);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        List<UserAccountInfo> userAccountInfos = getUserAccountInfos(record, cacheUserInfo);
        cacheUserInfo.setUserAccountInfos(userAccountInfos);
        CacheService.setCacheObj(redisTemplate, Cachekey.User.UID_PREFIX + record.getId(), cacheUserInfo);
        return cacheUserInfo;
    }

    private List<UserAccountInfo> getUserAccountInfos(UserInfo record, CacheUserInfo cacheUserInfo) {
        UserAccountExample userAccountExample = new UserAccountExample();
        userAccountExample.createCriteria().andUserIdEqualTo(record.getId());
        userAccountExample.setOrderByClause(" update_time asc ");
        List<UserAccount> userAccounts = userAccountService.selectByExample(userAccountExample);
        List<UserAccountInfo> userAccountInfos = new ArrayList<>();
        for (UserAccount userAccount : userAccounts) {
            UserAccountInfo userAccountInfo = new UserAccountInfo();
            try {
                BeanUtils.copyProperties(userAccountInfo, userAccount);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            if (userAccountInfo.getIsDefualt() == Constants.YES_OR_NO.YES) {
                cacheUserInfo.setDefualtAccount(userAccountInfo.getAccount());
                cacheUserInfo.setDefualtAccountId(userAccountInfo.getId());
                cacheUserInfo.setAccountBank(userAccountInfo.getBank());
            }
            userAccountInfos.add(userAccountInfo);
        }
        return userAccountInfos;
    }

    @Override
    public Map isOwner(String cellphone) {
        Map<String, String> m = new HashMap<>();
        m.put("cellphone", cellphone);
        String url = bsStaticsClient.getCodeName(Constants.CODE_TYPE.THREE_URL, "OWNER_QUERY_URL");
        String json = HttpClientUtil.doPost(url, m);
        if(StringUtils.isNotBlank(json)){
            Map map = (Map) JSONArray.parse(json);
            return (Map) map.get("result");
        }
        return null;
    }

    /**
     * 只查询用户id
     * @param example
     * @return
     */
    @Override
    public Set<String> getUserIdSetByExample(UserInfoExample example){
        return userInfoMapper.getUserIdListByExample(example);
    }
}