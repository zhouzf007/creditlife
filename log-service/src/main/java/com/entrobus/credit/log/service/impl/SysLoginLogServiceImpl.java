package com.entrobus.credit.log.service.impl;

import com.entrobus.credit.log.dao.SysLoginLogMapper;
import com.entrobus.credit.log.service.SysLoginLogService;
import com.entrobus.credit.pojo.log.SysLoginLog;
import com.entrobus.credit.pojo.log.SysLoginLogExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {
    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysLoginLogServiceImpl.class);

    public int countByExample(SysLoginLogExample example) {
        int count = this.sysLoginLogMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SysLoginLog selectByPrimaryKey(String id) {
        return this.sysLoginLogMapper.selectByPrimaryKey(id);
    }

    public List<SysLoginLog> selectByExample(SysLoginLogExample example) {
        return this.sysLoginLogMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.sysLoginLogMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysLoginLog record) {
        return this.sysLoginLogMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysLoginLog record) {
        return this.sysLoginLogMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(SysLoginLogExample example) {
        return this.sysLoginLogMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(SysLoginLog record, SysLoginLogExample example) {
        return this.sysLoginLogMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(SysLoginLog record, SysLoginLogExample example) {
        return this.sysLoginLogMapper.updateByExample(record, example);
    }

    public int insert(SysLoginLog record) {
        return this.sysLoginLogMapper.insert(record);
    }

    public int insertSelective(SysLoginLog record) {
        return this.sysLoginLogMapper.insertSelective(record);
    }
}