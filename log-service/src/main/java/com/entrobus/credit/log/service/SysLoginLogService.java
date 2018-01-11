package com.entrobus.credit.log.service;

import com.entrobus.credit.pojo.log.SysLoginLog;
import com.entrobus.credit.pojo.log.SysLoginLogExample;
import com.entrobus.credit.vo.log.SysLoginMsg;

import java.util.List;

public interface SysLoginLogService {
    int countByExample(SysLoginLogExample example);

    SysLoginLog selectByPrimaryKey(String id);

    List<SysLoginLog> selectByExample(SysLoginLogExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLoginLog record);

    int updateByPrimaryKey(SysLoginLog record);

    int deleteByExample(SysLoginLogExample example);

    int updateByExampleSelective(SysLoginLog record, SysLoginLogExample example);

    int updateByExample(SysLoginLog record, SysLoginLogExample example);

    int insert(SysLoginLog record);

    int insertSelective(SysLoginLog record);

    int log(SysLoginMsg msg);
}