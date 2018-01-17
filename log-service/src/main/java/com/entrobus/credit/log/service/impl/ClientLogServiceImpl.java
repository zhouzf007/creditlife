package com.entrobus.credit.log.service.impl;

import com.entrobus.credit.log.dao.ClientLogMapper;
import com.entrobus.credit.log.service.ClientLogService;
import com.entrobus.credit.pojo.log.ClientLog;
import com.entrobus.credit.pojo.log.ClientLogExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientLogServiceImpl implements ClientLogService {
    @Autowired
    private ClientLogMapper clientLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(ClientLogServiceImpl.class);

    public int countByExample(ClientLogExample example) {
        int count = this.clientLogMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public ClientLog selectByPrimaryKey(String id) {
        return this.clientLogMapper.selectByPrimaryKey(id);
    }

    public List<ClientLog> selectByExample(ClientLogExample example) {
        return this.clientLogMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.clientLogMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ClientLog record) {
        return this.clientLogMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ClientLog record) {
        return this.clientLogMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(ClientLogExample example) {
        return this.clientLogMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(ClientLog record, ClientLogExample example) {
        return this.clientLogMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(ClientLog record, ClientLogExample example) {
        return this.clientLogMapper.updateByExample(record, example);
    }

    public int insert(ClientLog record) {
        return this.clientLogMapper.insert(record);
    }

    public int insertSelective(ClientLog record) {
        return this.clientLogMapper.insertSelective(record);
    }
}