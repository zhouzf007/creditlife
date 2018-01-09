package com.entrobus.credit.user.services;

import com.entrobus.credit.pojo.user.UserAccount;
import com.entrobus.credit.pojo.user.UserAccountExample;
import java.util.List;

public interface UserAccountService {
    int countByExample(UserAccountExample example);

    UserAccount selectByPrimaryKey(String id);

    List<UserAccount> selectByExample(UserAccountExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    int deleteByExample(UserAccountExample example);

    int updateByExampleSelective(UserAccount record, UserAccountExample example);

    int updateByExample(UserAccount record, UserAccountExample example);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);
}