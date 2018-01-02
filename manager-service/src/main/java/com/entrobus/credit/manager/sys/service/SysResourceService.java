package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.pojo.manager.SysResource;
import com.entrobus.credit.pojo.manager.SysResourceExample;

import java.util.List;

public interface SysResourceService {
    int countByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(Long id);

    List<SysResource> selectByExample(SysResourceExample example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);

    int deleteByExample(SysResourceExample example);

    int updateByExampleSelective(SysResource record, SysResourceExample example);

    int updateByExample(SysResource record, SysResourceExample example);

    int insert(SysResource record);

    int insertSelective(SysResource record);
}