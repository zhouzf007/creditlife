package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.manager.dao.SysUserMapper;
import com.entrobus.credit.manager.sys.service.SysUserService;
import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.pojo.manager.SysUserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    public int countByExample(SysUserExample example) {
        int count = this.sysUserMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SysUser selectByPrimaryKey(Long id) {
        return this.sysUserMapper.selectByPrimaryKey(id);
    }

    public List<SysUser> selectByExample(SysUserExample example) {
        return this.sysUserMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.sysUserMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysUser record) {
        return this.sysUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysUser record) {
        return this.sysUserMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(SysUserExample example) {
        return this.sysUserMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(SysUser record, SysUserExample example) {
        return this.sysUserMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(SysUser record, SysUserExample example) {
        return this.sysUserMapper.updateByExample(record, example);
    }

    public int insert(SysUser record) {
        return this.sysUserMapper.insert(record);
    }

    public int insertSelective(SysUser record) {
        return this.sysUserMapper.insertSelective(record);
    }
}