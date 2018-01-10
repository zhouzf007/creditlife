package com.entrobus.credit.manager.bank.service.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.bank.service.PartiesService;
import com.entrobus.credit.manager.common.bean.PartiesExt;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.manager.common.service.ManagerCacheService;
import com.entrobus.credit.manager.dao.PartiesMapper;
import com.entrobus.credit.manager.sys.service.*;
import com.entrobus.credit.pojo.manager.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PartiesServiceImpl implements PartiesService {
    @Autowired
    private PartiesMapper partiesMapper;
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

    private static final Logger logger = LoggerFactory.getLogger(PartiesServiceImpl.class);

    public int countByExample(PartiesExample example) {
        int count = this.partiesMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Parties selectByPrimaryKey(String id) {
        return this.partiesMapper.selectByPrimaryKey(id);
    }

    public List<Parties> selectByExample(PartiesExample example) {
        return this.partiesMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.partiesMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Parties record) {
        return this.partiesMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Parties record) {
        return this.partiesMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(PartiesExample example) {
        return this.partiesMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Parties record, PartiesExample example) {
        return this.partiesMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Parties record, PartiesExample example) {
        return this.partiesMapper.updateByExample(record, example);
    }

    public int insert(Parties record) {
        return this.partiesMapper.insert(record);
    }

    public int insertSelective(Parties record) {
        return this.partiesMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public WebResult add(PartiesExt partiesExt) {
        //查询是否已经存在该资金方
        if(checkName(partiesExt.getName())){
            return WebResult.error("已经存在该资金方");
        }
        //查询手机号是否已经被注册
        if(checkContractMobile(partiesExt.getContractMobile())){
            return WebResult.error("手机号码已被绑定其他资金方");
        }
        //查询手机号是否已注册社区贷资金平台
        if(sysUserService.checkUserName(partiesExt.getContractMobile(),Constants.PLATFORM.bank)){
            return WebResult.error("手机号码已注册社区贷资金平台，请更换手机重试");
        }
        SysLoginUserInfo userInfo = managerCacheService.getCurrLoginUser();
        partiesExt.setCreateTime(new Date());
        partiesExt.setCreateOperator(userInfo.getId()+"");
        partiesExt.setDeleteFlag(Constants.DeleteFlag.NO);
        partiesExt.setId(GUIDUtil.genRandomGUID());
        Integer result = this.insertSelective(partiesExt);
        if(result>0){
            //add资金方sysRole
            SysRole sysRole = new SysRole();
            sysRole.setOrgId(partiesExt.getId());
            sysRole.setRoleName("系统管理员");
            sysRole.setRemark("系统生成资金方时生成的默认角色");
            sysRole.setPlatform(Constants.PLATFORM.bank);
            sysRole.setCreateTime(new Date());
            sysRole.setCreateUser(userInfo.getId());
            sysRole.setDeleteFlag(Constants.DeleteFlag.NO);
            sysRoleService.insertSelective(sysRole);
            //查询平台所有资源
            List<SysResource> resources = sysResourceService.getSysResourceByPlatform(Constants.PLATFORM.bank);
            //过滤数据中心+资源管理
            List<Long> resourceIds = new ArrayList<>();
            for (SysResource sysResource : resources){
                if("data_center.html".equals(sysResource.getUrl()) || "sys_resource_info.html".equals(sysResource.getUrl())) continue;
                resourceIds.add(sysResource.getId());
            }
            //赋予角色资源
            sysRoleResourceService.save(sysRole.getId(),resourceIds);
            //添加对应该资金方的超级管理员账号,赋予账号角色
            List<Long> roleIds = new ArrayList<>();
            roleIds.add(sysRole.getId());
            SysUserExt sysUserExt = new SysUserExt();
            sysUserExt.setOrgId(partiesExt.getId());
            sysUserExt.setUsername(partiesExt.getContractMobile());
            sysUserExt.setPassword(partiesExt.getPassword());
            sysUserExt.setCellphone(partiesExt.getContractMobile());
            sysUserExt.setRealName(partiesExt.getContractName());
            sysUserExt.setSex(0);
            sysUserExt.setAge(0);
            sysUserExt.setStatus(0);
            sysUserExt.setCreateUser(userInfo.getId());
            sysUserExt.setUpdateUser(userInfo.getId());
            sysUserExt.setRemark("系统生成资金方时生成的默认账号");
            sysUserExt.setPlatform(Constants.PLATFORM.bank);
            sysUserExt.setRoleIdList(roleIds);
            sysUserService.save(sysUserExt);
            return WebResult.ok("添加成功");
        }else{
            return WebResult.error("添加失败");
        }
    }

    @Override
    public boolean checkName(String name) {
        PartiesExample partiesExample = new PartiesExample();
        partiesExample.createCriteria().andNameEqualTo(name)
                .andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        List<Parties> partiesList = this.selectByExample(partiesExample);
        if(ConversionUtil.isNotEmptyParameter(partiesList)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkContractMobile(String mobile) {
        //查询手机号是否已经被注册
        PartiesExample partiesExample = new PartiesExample();
        partiesExample.createCriteria().andContractMobileEqualTo(mobile)
                .andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        List<Parties> partiesList = this.selectByExample(partiesExample);
        if(ConversionUtil.isNotEmptyParameter(partiesList)){
            return true;
        }
        return false;
    }
}