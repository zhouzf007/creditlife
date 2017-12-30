package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.pojo.manager.SysRoleResource;
import com.entrobus.credit.pojo.manager.SysRoleResourceExample;

import java.util.List;

public interface SysRoleResourceService {
    int countByExample(SysRoleResourceExample example);

    SysRoleResource selectByPrimaryKey(Long id);

    List<SysRoleResource> selectByExample(SysRoleResourceExample example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleResource record);

    int updateByPrimaryKey(SysRoleResource record);

    int deleteByExample(SysRoleResourceExample example);

    int updateByExampleSelective(SysRoleResource record, SysRoleResourceExample example);

    int updateByExample(SysRoleResource record, SysRoleResourceExample example);

    int insert(SysRoleResource record);

    int insertSelective(SysRoleResource record);
}