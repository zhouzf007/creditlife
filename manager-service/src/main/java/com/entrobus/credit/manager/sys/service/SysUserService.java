package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.pojo.manager.SysUserExample;

import java.util.List;

public interface SysUserService {
    int countByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long id);

    List<SysUser> selectByExample(SysUserExample example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    int deleteByExample(SysUserExample example);

    int updateByExampleSelective(SysUser record, SysUserExample example);

    int updateByExample(SysUser record, SysUserExample example);

    int insert(SysUser record);

    int insertSelective(SysUser record);
}