package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.pojo.manager.SysUserRole;
import com.entrobus.credit.pojo.manager.SysUserRoleExample;

import java.util.List;

public interface SysUserRoleService {
    int countByExample(SysUserRoleExample example);

    SysUserRole selectByPrimaryKey(Long id);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    int deleteByExample(SysUserRoleExample example);

    int updateByExampleSelective(SysUserRole record, SysUserRoleExample example);

    int updateByExample(SysUserRole record, SysUserRoleExample example);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}