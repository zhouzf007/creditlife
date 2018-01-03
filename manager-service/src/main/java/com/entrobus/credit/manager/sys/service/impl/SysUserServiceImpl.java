package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.common.SysConstants;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.manager.dao.SysUserMapper;
import com.entrobus.credit.manager.sys.security.shiro.ShiroUtils;
import com.entrobus.credit.manager.sys.service.SysResourceService;
import com.entrobus.credit.manager.sys.service.SysUserRoleService;
import com.entrobus.credit.manager.sys.service.SysUserService;
import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.pojo.manager.SysUserExample;
import com.entrobus.credit.pojo.manager.SysUserRoleExample;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysResourceService sysResourceService;

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    public int countByExample(SysUserExample example) {
        int count = this.sysUserMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SysUser selectByPrimaryKey(Long id) {
        return this.sysUserMapper.selectByPrimaryKey(id);
    }

    public List<SysUser> selectByExample(SysUserExample example) {
        return this.sysUserMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.sysUserMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysUser record) {
        return this.sysUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysUser record) {
        return this.sysUserMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(SysUserExample example) {
        return this.sysUserMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(SysUser record, SysUserExample example) {
        return this.sysUserMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(SysUser record, SysUserExample example) {
        return this.sysUserMapper.updateByExample(record, example);
    }

    public int insert(SysUser record) {
        return this.sysUserMapper.insert(record);
    }

    public int insertSelective(SysUser record) {
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        return this.sysUserMapper.insertSelective(record);
    }

    @Override
    public int updatePassword(Long sysLoginUserId, String password, String newPassword) {
        SysUser sysUser = selectByPrimaryKey(sysLoginUserId);
        //sha256加密
        password = new Sha256Hash(password,sysUser.getSalt()).toHex();
        //旧密码正确后才执行修改密码操作
        if(sysUser.getPassword().equals(password)){
            //修改密码的同时，把用于密码加密的盐也一起修改
            //生成20位长度的随机数，用作密码加密的盐
            String salt = RandomStringUtils.randomAlphanumeric(20);
            //sha256加密
            newPassword = ShiroUtils.sha256(newPassword,salt);
            sysUser.setPassword(newPassword);
            sysUser.setSalt(salt);
            sysUser.setUpdateUser(sysLoginUserId);
            sysUser.setUpdateTime(new Date());
            return updateByPrimaryKeySelective(sysUser);
        }
        return 0;
    }

    @Transactional //开启事务（涉及到多表操作）
    @Override
    public void save(SysUserExt sysUserExt) {
        SysUser sysUser = new SysUser();
        try {
            BeanUtils.copyProperties(sysUser,sysUserExt);
            sysUser.setCreateTime(new Date());
            sysUser.setUpdateTime(new Date());
            sysUser.setDeleteFlag(Constants.DeleteFlag.NO);
            //生成20位长度的随机数，用作密码加密的盐
            String salt = RandomStringUtils.randomAlphanumeric(20);
            //将密码使用sha256加密
            sysUser.setPassword(ShiroUtils.sha256(sysUser.getPassword(),salt));
            sysUser.setSalt(salt);
            //保存系统用户，保存成功后会返回主键的值
            insertSelective(sysUser);
            if(StringUtils.isNotEmpty(sysUserExt.getRoleIds())){
                List<Long> roleIdList = new ArrayList<>();
                String[] idArr = sysUserExt.getRoleIds().split(",");
                for(String id : idArr){
                    roleIdList.add(Long.parseLong(id));
                }
                sysUserExt.setRoleIdList(roleIdList);
                //保存用户与角色关系
                sysUserRoleService.save(sysUser.getId(),sysUserExt.getRoleIdList());
            }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(SysUserExt sysUserExt) {
        SysUser sysUser = new SysUser();
        try {
            if(StringUtils.isBlank(sysUserExt.getPassword())){
                sysUserExt.setPassword(null);
            }else{
                //修改密码的同时，把用于密码加密的盐也一起修改
                //生成20位长度的随机数，用作密码加密的盐
                String salt = RandomStringUtils.randomAlphanumeric(20);
                //将密码使用sha256加密
                sysUserExt.setPassword(ShiroUtils.sha256(sysUser.getPassword(),salt));
                sysUserExt.setSalt(salt);
            }
            //相同属性复制
            BeanUtils.copyProperties(sysUser,sysUserExt);
            sysUser.setUpdateTime(new Date());
            //修改系统用户
            updateByPrimaryKeySelective(sysUser);
            if(StringUtils.isNotEmpty(sysUserExt.getRoleIds())){
                List<Long> roleIdList = new ArrayList<>();
                String[] idArr = sysUserExt.getRoleIds().split(",");
                for(String id : idArr){
                    roleIdList.add(Long.parseLong(id));
                }
                sysUserExt.setRoleIdList(roleIdList);
            }
            //保存用户与角色关系
            sysUserRoleService.save(sysUser.getId(),sysUserExt.getRoleIdList());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Transactional //开启事务（涉及到多表操作）
    @Override
    public void delete(Long deleteUserId, List<Long> idList) {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andIdIn(idList);
        SysUser sysUser = new SysUser();
        sysUser.setDeleteFlag(Constants.DeleteFlag.YES);
        sysUser.setDeleteUser(deleteUserId);
        sysUser.setDeleteTime(new Date());
        updateByExampleSelective(sysUser,userExample);//逻辑删除

        //删除用户与角色关系
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdIn(idList);
        sysUserRoleService.deleteByExample(userRoleExample);
    }

    @Override
    public WebResult login(String username, String password,Integer platform) {
        //根据用户名查找用户
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria()
                .andDeleteFlagEqualTo(Constants.DeleteFlag.NO)
                .andUsernameEqualTo(username)
                .andPlatformEqualTo(platform);
        List<SysUser> sysUserList = selectByExample(userExample);
        if(CollectionUtils.isEmpty(sysUserList)){
            return WebResult.error("用户名或者密码不正确");
        }
        SysUser sysUser = sysUserList.get(0);
        password = ShiroUtils.sha256(password,sysUser.getSalt());
        //密码错误
        if(!password.equals(sysUser.getPassword())) {
            return WebResult.error("用户名或者密码不正确");
        }
        //账号已经被禁用
        if(sysUser.getStatus()== SysConstants.USER_STATUS.FROZEN){
            return WebResult.error("账号已被锁定,请联系管理员");
        }
        //登录成功，生成登录token
        SysLoginUserInfo sysLoginUserInfo = new SysLoginUserInfo();
        try {
            //相同属性复制
            BeanUtils.copyProperties(sysLoginUserInfo,sysUser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //生成32位token
        String token = GUIDUtil.genRandomGUID();
        //获取用户权限
        Set<String> permsSet = sysResourceService.getUserPerms(sysUser.getId(),platform);
        sysLoginUserInfo.setPerms(permsSet);
        //将登录信息缓存到redis
        CacheService.setCacheObj(redisTemplate,token,sysLoginUserInfo);
        return WebResult.ok().put("token",token).put("perms",permsSet);
    }
}