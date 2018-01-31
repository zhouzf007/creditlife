package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.manager.dao.SysRoleResourceMapper;
import com.entrobus.credit.manager.sys.service.SysRoleResourceService;
import com.entrobus.credit.pojo.manager.SysRoleResource;
import com.entrobus.credit.pojo.manager.SysRoleResourceExample;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
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

    @Override
    public void save(Long roleId, List<Long> resourceIdList) {
        //先删除角色与资源的关系
        deleteByRoleId(roleId);
        if(CollectionUtils.isNotEmpty(resourceIdList)){
            List<SysRoleResource> roleResourceList = new ArrayList<>();
            for(Long resourceId:resourceIdList){
                SysRoleResource roleResource = new SysRoleResource();
                roleResource.setResourceId(resourceId);
                roleResource.setRoleId(roleId);
                roleResource.setCreateTime(new Date());
                roleResourceList.add(roleResource);
            }
            //批量保存角色能够查看的资源
            sysRoleResourceMapper.insertBatchSelective(roleResourceList);
        }
    }

    @Override
    public int deleteByRoleId(Long roleId) {
        SysRoleResourceExample example = new SysRoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return deleteByExample(example);
    }
}