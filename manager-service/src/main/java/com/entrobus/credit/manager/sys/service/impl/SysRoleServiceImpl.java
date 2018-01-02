package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.manager.common.bean.SysRoleExt;
import com.entrobus.credit.manager.dao.SysRoleMapper;
import com.entrobus.credit.manager.sys.service.SysRoleResourceService;
import com.entrobus.credit.manager.sys.service.SysRoleService;
import com.entrobus.credit.manager.sys.service.SysUserRoleService;
import com.entrobus.credit.pojo.manager.SysRole;
import com.entrobus.credit.pojo.manager.SysRoleExample;
import com.entrobus.credit.pojo.manager.SysRoleResourceExample;
import com.entrobus.credit.pojo.manager.SysUserRoleExample;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

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

    @Transactional //开启事务（涉及到多表操作）
    @Override
    public void save(SysRoleExt role) {
        SysRole sysRole = new SysRole();
        try {
            BeanUtils.copyProperties(sysRole,role);
            sysRole.setCreateTime(new Date());
            sysRole.setUpdateTime(new Date());
            sysRole.setDeleteFlag(Constants.DeleteFlag.NO);
            //保存系统角色，保存成功后会返回主键的值
            insertSelective(sysRole);
            if(StringUtils.isNotEmpty(role.getResourceIds())){
                List<Long> resourceIdList = new ArrayList<>();
                String[] idArr = role.getResourceIds().split(",");
                for(String id : idArr){
                    resourceIdList.add(Long.parseLong(id));
                }
                role.setResourceIdList(resourceIdList);
                //保存角色与资源关系
                sysRoleResourceService.save(sysRole.getId(),role.getResourceIdList());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Transactional //开启事务（涉及到多表操作）
    @Override
    public void update(SysRoleExt role) {
        SysRole sysRole = new SysRole();
        try {
            BeanUtils.copyProperties(sysRole,role);
            sysRole.setUpdateTime(new Date());
            //修改系统角色
            updateByPrimaryKeySelective(sysRole);
            if(StringUtils.isNotEmpty(role.getResourceIds())){
                List<Long> resourceIdList = new ArrayList<>();
                String[] idArr = role.getResourceIds().split(",");
                for(String id : idArr){
                    resourceIdList.add(Long.parseLong(id));
                }
                role.setResourceIdList(resourceIdList);
            }
            //保存角色与资源关系
            sysRoleResourceService.save(sysRole.getId(),role.getResourceIdList());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Transactional //开启事务（涉及到多表操作）
    @Override
    public void delete(Long deleteUserId, List<Long> idList) {
        SysRoleExample roleExample = new SysRoleExample();
        roleExample.createCriteria().andIdIn(idList);
        SysRole sysRole = new SysRole();
        sysRole.setDeleteFlag(Constants.DeleteFlag.YES);
        sysRole.setDeleteUser(deleteUserId);
        sysRole.setDeleteTime(new Date());
        //1.删除角色（逻辑删除）
        updateByExampleSelective(sysRole,roleExample);
        //2.删除角色与用户的关系
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andRoleIdIn(idList);
        sysUserRoleService.deleteByExample(userRoleExample);//物理删除
        //3.删除角色与资源的关系
        SysRoleResourceExample roleResourceExample = new SysRoleResourceExample();
        roleResourceExample.createCriteria().andRoleIdIn(idList);
        sysRoleResourceService.deleteByExample(roleResourceExample);//物理删除
    }
}