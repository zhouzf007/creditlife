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

    /**
     * 保存角色资源信息
     * @param roleId
     * @param resourceIdList
     */
    void save(Long roleId, List<Long> resourceIdList);

    /**
     * 通过角色ID删除资源
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);
}