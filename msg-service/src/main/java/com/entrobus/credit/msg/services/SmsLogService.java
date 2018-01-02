package com.entrobus.credit.msg.services;

import com.entrobus.credit.pojo.msg.SmsLog;
import com.entrobus.credit.pojo.msg.SmsLogExample;
import java.util.List;

public interface SmsLogService {
    int countByExample(SmsLogExample example);

    SmsLog selectByPrimaryKey(String id);

    List<SmsLog> selectByExample(SmsLogExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SmsLog record);

    int updateByPrimaryKey(SmsLog record);

    int deleteByExample(SmsLogExample example);

    int updateByExampleSelective(SmsLog record, SmsLogExample example);

    int updateByExample(SmsLog record, SmsLogExample example);

    int insert(SmsLog record);

    int insertSelective(SmsLog record);

    int saveSmsLog(String mobile,Integer type,String title,String content,String result);

}