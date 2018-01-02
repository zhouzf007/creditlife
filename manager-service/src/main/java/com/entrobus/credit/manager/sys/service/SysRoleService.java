package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.pojo.manager.SysRole;
import com.entrobus.credit.pojo.manager.SysRoleExample;

import java.util.List;

public interface SysRoleService {
    int countByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Long id);

    List<SysRole> selectByExample(SysRoleExample example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    int deleteByExample(SysRoleExample example);

    int updateByExampleSelective(SysRole record, SysRoleExample example);

    int updateByExample(SysRole record, SysRoleExample example);

    int insert(SysRole record);

    int insertSelective(SysRole record);
}