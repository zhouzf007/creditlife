package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.manager.dao.SysRoleMapper;
import com.entrobus.credit.manager.sys.service.SysRoleService;
import com.entrobus.credit.pojo.manager.SysRole;
import com.entrobus.credit.pojo.manager.SysRoleExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    public int countByExample(SysRoleExample example) {
        int count = this.sysRoleMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SysRole selectByPrimaryKey(Long id) {
        return this.sysRoleMapper.selectByPrimaryKey(id);
    }

    public List<SysRole> selectByExample(SysRoleExample example) {
        return this.sysRoleMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.sysRoleMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysRole record) {
        return this.sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysRole record) {
        return this.sysRoleMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(SysRoleExample example) {
        return this.sysRoleMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(SysRole record, SysRoleExample example) {
        return this.sysRoleMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(SysRole record, SysRoleExample example) {
        return this.sysRoleMapper.updateByExample(record, example);
    }

    public int insert(SysRole record) {
        return this.sysRoleMapper.insert(record);
    }

    public int insertSelective(SysRole record) {
        return this.sysRoleMapper.insertSelective(record);
    }
}