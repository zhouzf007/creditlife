package com.entrobus.credit.log.service;

import com.entrobus.credit.pojo.log.ClientLog;
import com.entrobus.credit.pojo.log.ClientLogExample;

import java.util.List;

public interface ClientLogService {
    int countByExample(ClientLogExample example);

    ClientLog selectByPrimaryKey(String id);

    List<ClientLog> selectByExample(ClientLogExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ClientLog record);

    int updateByPrimaryKey(ClientLog record);

    int deleteByExample(ClientLogExample example);

    int updateByExampleSelective(ClientLog record, ClientLogExample example);

    int updateByExample(ClientLog record, ClientLogExample example);

    int insert(ClientLog record);

    int insertSelective(ClientLog record);
}