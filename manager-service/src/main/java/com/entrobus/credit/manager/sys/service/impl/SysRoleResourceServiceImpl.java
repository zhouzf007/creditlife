package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.manager.dao.SysRoleResourceMapper;
import com.entrobus.credit.manager.sys.service.SysRoleResourceService;
import com.entrobus.credit.pojo.manager.SysRoleResource;
import com.entrobus.credit.pojo.manager.SysRoleResourceExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService {
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysRoleResourceServiceImpl.class);

    public int countByExample(SysRoleResourceExample example) {
        int count = this.sysRoleResourceMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SysRoleResource selectByPrimaryKey(Long id) {
        return this.sysRoleResourceMapper.selectByPrimaryKey(id);
    }

    public List<SysRoleResource> selectByExample(SysRoleResourceExample example) {
        return this.sysRoleResourceMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.sysRoleResourceMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysRoleResource record) {
        return this.sysRoleResourceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysRoleResource record) {
        return this.sysRoleResourceMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(SysRoleResourceExample example) {
        return this.sysRoleResourceMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(SysRoleResource record, SysRoleResourceExample example) {
        return this.sysRoleResourceMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(SysRoleResource record, SysRoleResourceExample example) {
        return this.sysRoleResourceMapper.updateByExample(record, example);
    }

    public int insert(SysRoleResource record) {
        return this.sysRoleResourceMapper.insert(record);
    }

    public int insertSelective(SysRoleResource record) {
        return this.sysRoleResourceMapper.insertSelective(record);
    }
}