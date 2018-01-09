package com.entrobus.credit.user.services;

import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.pojo.user.UserInfoExample;
import java.util.List;

public interface UserInfoService {
    int countByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(String id);

    List<UserInfo> selectByExample(UserInfoExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int deleteByExample(UserInfoExample example);

    int updateByExampleSelective(UserInfo record, UserInfoExample example);

    int updateByExample(UserInfo record, UserInfoExample example);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}