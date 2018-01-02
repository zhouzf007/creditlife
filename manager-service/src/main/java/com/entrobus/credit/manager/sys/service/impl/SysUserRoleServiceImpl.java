package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.manager.dao.SysUserRoleMapper;
import com.entrobus.credit.manager.sys.service.SysUserRoleService;
import com.entrobus.credit.pojo.manager.SysUserRole;
import com.entrobus.credit.pojo.manager.SysUserRoleExample;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    public int countByExample(SysUserRoleExample example) {
        int count = this.sysUserRoleMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SysUserRole selectByPrimaryKey(Long id) {
        return this.sysUserRoleMapper.selectByPrimaryKey(id);
    }

    public List<SysUserRole> selectByExample(SysUserRoleExample example) {
        return this.sysUserRoleMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.sysUserRoleMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysUserRole record) {
        return this.sysUserRoleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysUserRole record) {
        return this.sysUserRoleMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(SysUserRoleExample example) {
        return this.sysUserRoleMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(SysUserRole record, SysUserRoleExample example) {
        return this.sysUserRoleMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(SysUserRole record, SysUserRoleExample example) {
        return this.sysUserRoleMapper.updateByExample(record, example);
    }

    public int insert(SysUserRole record) {
        return this.sysUserRoleMapper.insert(record);
    }

    public int insertSelective(SysUserRole record) {
        return this.sysUserRoleMapper.insertSelective(record);
    }

    @Override
    public void save(Long userId, List<Long> roleIdList) {
        if(CollectionUtils.isNotEmpty(roleIdList)){
            List<SysUserRole> userRoleList = new ArrayList<>();
            for(Long roleId:roleIdList){
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                sysUserRole.setCreateTime(new Date());
                userRoleList.add(sysUserRole);
            }
            //先删除原有的角色
            deleteByUserId(userId);
            //批量保存
            sysUserRoleMapper.insertBatchSelective(userRoleList);
        }
    }

    @Override
    public List<Long> getRoleIdList(Long userId) {
        List<Long> roleIdList = new ArrayList<>();
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<SysUserRole> userRoleList = selectByExample(example);
        if(CollectionUtils.isNotEmpty(userRoleList)){
            for(SysUserRole role:userRoleList){
                roleIdList.add(role.getRoleId());
            }
        }
        return roleIdList;
    }

    @Override
    public int deleteByUserId(Long userId) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return deleteByExample(example);//物理删除
    }
}