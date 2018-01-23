package com.entrobus.credit.manager.bank.service.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.bank.service.OrganizationService;
import com.entrobus.credit.manager.common.bean.OrganizationExt;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.manager.common.service.ManagerCacheService;
import com.entrobus.credit.manager.dao.OrganizationMapper;
import com.entrobus.credit.manager.sys.security.shiro.ShiroUtils;
import com.entrobus.credit.manager.sys.service.*;
import com.entrobus.credit.pojo.manager.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private ManagerCacheService managerCacheService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    public int countByExample(OrganizationExample example) {
        int count = this.organizationMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Organization selectByPrimaryKey(String id) {
        return this.organizationMapper.selectByPrimaryKey(id);
    }

    public List<Organization> selectByExample(OrganizationExample example) {
        return this.organizationMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.organizationMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Organization record) {
        return this.organizationMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Organization record) {
        return this.organizationMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(OrganizationExample example) {
        return this.organizationMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Organization record, OrganizationExample example) {
        return this.organizationMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Organization record, OrganizationExample example) {
        return this.organizationMapper.updateByExample(record, example);
    }

    public int insert(Organization record) {
        return this.organizationMapper.insert(record);
    }

    public int insertSelective(Organization record) {
        return this.organizationMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public WebResult add(OrganizationExt organizationExt) {
        //查询是否已经存在该资金方
        if(checkName(organizationExt.getName())){
            return WebResult.fail("已经存在该资金方");
        }
        //查询手机号是否已经被注册
        if(checkContractMobile(organizationExt.getContractMobile())){
            return WebResult.fail("手机号码已被绑定其他资金方");
        }
        //查询手机号是否已注册社区贷资金平台
        if(sysUserService.checkUserName(organizationExt.getContractMobile())){
            return WebResult.fail("手机号码已注册社区贷资金平台，请更换手机重试");
        }
        SysLoginUserInfo userInfo = managerCacheService.getCurrLoginUser();
        organizationExt.setCreateTime(new Date());
        organizationExt.setCreateOperator(userInfo.getId()+"");
        organizationExt.setDeleteFlag(Constants.DELETE_FLAG.NO);
        organizationExt.setId(GUIDUtil.genRandomGUID());
        Integer result = this.insertSelective(organizationExt);
        if(result>0){
            //add资金方sysRole
            SysRole sysRole = new SysRole();
            sysRole.setOrgId(organizationExt.getId());
            sysRole.setRoleName("系统管理员");
            sysRole.setRemark("系统生成资金方时生成的默认角色");
            sysRole.setPlatform(Constants.PLATFORM.BANK);
            sysRole.setCreateTime(new Date());
            sysRole.setCreateUser(userInfo.getId());
            sysRole.setDeleteFlag(Constants.DELETE_FLAG.NO);
            sysRoleService.insertSelective(sysRole);
            //查询平台所有资源
            List<SysResource> resources = sysResourceService.getSysResourceByPlatform(Constants.PLATFORM.BANK);
            //过滤数据中心+资源管理
            List<Long> resourceIds = new ArrayList<>();
            for (SysResource sysResource : resources){
                if("sys_resource_info.html".equals(sysResource.getUrl())) continue;
                resourceIds.add(sysResource.getId());
            }
            //赋予角色资源
            sysRoleResourceService.save(sysRole.getId(),resourceIds);
            //添加对应该资金方的超级管理员账号,赋予账号角色
            List<Long> roleIds = new ArrayList<>();
            roleIds.add(sysRole.getId());
            SysUserExt sysUserExt = new SysUserExt();
            sysUserExt.setOrgId(organizationExt.getId());
            sysUserExt.setUsername(organizationExt.getContractMobile());
            sysUserExt.setPassword(organizationExt.getPassword());
            sysUserExt.setCellphone(organizationExt.getContractMobile());
            sysUserExt.setRealName(organizationExt.getContractName());
            sysUserExt.setSex(0);
            sysUserExt.setAge(0);
            sysUserExt.setStatus(0);
            sysUserExt.setCreateUser(userInfo.getId());
            sysUserExt.setUpdateUser(userInfo.getId());
            sysUserExt.setRemark("系统生成资金方时生成的默认账号");
            sysUserExt.setPlatform(Constants.PLATFORM.BANK);
            sysUserExt.setRoleIdList(roleIds);
            sysUserService.save(sysUserExt);
            Organization organization = new Organization();
            organization.setId(organizationExt.getId());
            organization.setSysUserId(sysUserExt.getId());
            this.updateByPrimaryKeySelective(organization);
            return WebResult.ok("添加成功");
        }else{
            return WebResult.fail("添加失败");
        }
    }

    @Transactional
    @Override
    public WebResult edit(OrganizationExt organizationExt) {
        SysLoginUserInfo userInfo = managerCacheService.getCurrLoginUser();
        Organization organization = this.selectByPrimaryKey(organizationExt.getId());
        //查询是否已经存在该资金方
        if(checkName(organizationExt.getName(), organizationExt.getId())){
            return WebResult.fail("已经存在该资金方");
        }
        //查询手机号是否已经被注册
        if(checkContractMobile(organizationExt.getContractMobile(), organizationExt.getId())){
            return WebResult.fail("手机号码已被绑定其他资金方");
        }
        //查询手机号是否已注册社区贷资金平台
        if(sysUserService.checkUserName(organizationExt.getContractMobile(),organization.getSysUserId())){
            return WebResult.fail("手机号码已注册社区贷资金平台，请更换手机重试");
        }
        //修改资金方
        organizationExt.setUpdateTime(new Date());
        organizationExt.setUpdateOperator(userInfo.getId()+"");
        this.updateByPrimaryKeySelective(organizationExt);
        //修改对应账号
        SysUser sysUser = new SysUser();
        sysUser.setId(organization.getSysUserId());
        sysUser.setUsername(organizationExt.getContractMobile());
        String salt = RandomStringUtils.randomAlphanumeric(20);
        //将密码使用sha256加密
        sysUser.setPassword(ShiroUtils.sha256(organizationExt.getPassword(),salt));
        sysUser.setSalt(salt);
        sysUser.setStatus(organizationExt.getState());
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateUser(userInfo.getId());
        sysUserService.updateByPrimaryKeySelective(sysUser);
        //冻结资金方下面所有账号
        if(organizationExt.getState()==Constants.ORGANIZATION_STATE.FROZEN){
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andOrgIdEqualTo(organizationExt.getId())
                    .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
            List<SysUser> sysUsers = sysUserService.selectByExample(sysUserExample);
            if(ConversionUtil.isNotEmptyParameter(sysUsers)){
                List<Long> sysUserIdList = new ArrayList<>();
                for (SysUser user : sysUsers){
                    sysUserIdList.add(user.getId());
                }
                SysUserExample updateUserExample = new SysUserExample();
                updateUserExample.createCriteria().andIdIn(sysUserIdList);
                SysUser record = new SysUser();
                record.setStatus(1);
                record.setUpdateUser(userInfo.getId());
                record.setUpdateTime(new Date());
                sysUserService.updateByExampleSelective(record,updateUserExample);
                //注销登录
                managerCacheService.batchLogout(sysUserIdList);
            }
        }
        return WebResult.ok("编辑成功");
    }

    @Transactional
    @Override
    public WebResult delete(String id) {
        //删除资金方
        this.deleteByPrimaryKey(id);
        //删除下属账号
        SysUserExample example = new SysUserExample();
        example.createCriteria().andOrgIdEqualTo(id);
        sysUserService.deleteByExample(example);
        //删除下属角色
        SysRoleExample roleExample = new SysRoleExample();
        roleExample.createCriteria().andOrgIdEqualTo(id);
        List<SysRole> roleList = sysRoleService.selectByExample(roleExample);
        List<Long> roleIdList = new ArrayList<>();
        for (SysRole role : roleList){
            roleIdList.add(role.getId());
        }
        if(ConversionUtil.isNotEmptyParameter(roleIdList)){
            SysUserRoleExample userRoleExample = new SysUserRoleExample();
            userRoleExample.createCriteria().andRoleIdIn(roleIdList);
            sysUserRoleService.deleteByExample(userRoleExample);
            SysRoleResourceExample roleResourceExample = new SysRoleResourceExample();
            roleResourceExample.createCriteria().andRoleIdIn(roleIdList);
            sysRoleResourceService.deleteByExample(roleResourceExample);
            sysRoleService.deleteByExample(roleExample);
        }
        return WebResult.ok();
    }

    @Override
    public boolean checkName(String name) {
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.createCriteria().andNameEqualTo(name)
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<Organization> organizationList = this.selectByExample(organizationExample);
        if(ConversionUtil.isNotEmptyParameter(organizationList)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkName(String name, String id) {
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.createCriteria().andNameEqualTo(name)
                .andIdNotEqualTo(id)
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<Organization> organizationList = this.selectByExample(organizationExample);
        if(ConversionUtil.isNotEmptyParameter(organizationList)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkContractMobile(String mobile, String id) {
        //查询手机号是否已经被注册
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.createCriteria().andContractMobileEqualTo(mobile)
                .andIdNotEqualTo(id)
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<Organization> organizationList = this.selectByExample(organizationExample);
        if(ConversionUtil.isNotEmptyParameter(organizationList)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkContractMobile(String mobile) {
        //查询手机号是否已经被注册
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.createCriteria().andContractMobileEqualTo(mobile)
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<Organization> organizationList = this.selectByExample(organizationExample);
        if(ConversionUtil.isNotEmptyParameter(organizationList)){
            return true;
        }
        return false;
    }
}