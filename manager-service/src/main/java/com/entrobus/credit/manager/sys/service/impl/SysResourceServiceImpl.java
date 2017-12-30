package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.manager.dao.SysResourceMapper;
import com.entrobus.credit.manager.sys.service.SysResourceService;
import com.entrobus.credit.pojo.manager.SysResource;
import com.entrobus.credit.pojo.manager.SysResourceExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysResourceServiceImpl implements SysResourceService {
    @Autowired
    private SysResourceMapper sysResourceMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysResourceServiceImpl.class);

    public int countByExample(SysResourceExample example) {
        int count = this.sysResourceMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SysResource selectByPrimaryKey(Long id) {
        return this.sysResourceMapper.selectByPrimaryKey(id);
    }

    public List<SysResource> selectByExample(SysResourceExample example) {
        return this.sysResourceMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.sysResourceMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysResource record) {
        return this.sysResourceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysResource record) {
        return this.sysResourceMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(SysResourceExample example) {
        return this.sysResourceMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(SysResource record, SysResourceExample example) {
        return this.sysResourceMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(SysResource record, SysResourceExample example) {
        return this.sysResourceMapper.updateByExample(record, example);
    }

    public int insert(SysResource record) {
        return this.sysResourceMapper.insert(record);
    }

    public int insertSelective(SysResource record) {
        return this.sysResourceMapper.insertSelective(record);
    }
}