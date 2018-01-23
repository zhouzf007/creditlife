package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.common.SysConstants;
import com.entrobus.credit.manager.common.bean.LoginVo;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.manager.common.service.ManagerCacheService;
import com.entrobus.credit.manager.dao.SysUserMapper;
import com.entrobus.credit.manager.sys.security.shiro.ShiroUtils;
import com.entrobus.credit.manager.sys.service.LogService;
import com.entrobus.credit.manager.sys.service.SysResourceService;
import com.entrobus.credit.manager.sys.service.SysUserRoleService;
import com.entrobus.credit.manager.sys.service.SysUserService;
import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.pojo.manager.SysUserExample;
import com.entrobus.credit.pojo.manager.SysUserRoleExample;
import com.entrobus.credit.vo.log.SysLoginMsg;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private LogService logService;
    @Autowired
    private ManagerCacheService managerCacheService;

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
    public WebResult save(SysUserExt sysUserExt) {
        //判断要新增的用户的用户名是否已经存在
        SysUser existUser = getUserByUserName(sysUserExt.getUsername());
        if(existUser != null){
            return WebResult.fail("该用户已经存在，请不要重复创建！");
        }
        SysUser sysUser = new SysUser();
        try {
            BeanUtils.copyProperties(sysUser,sysUserExt);
            sysUser.setCreateTime(new Date());
            sysUser.setUpdateTime(new Date());
            sysUser.setDeleteFlag(Constants.DELETE_FLAG.NO);
            //生成20位长度的随机数，用作密码加密的盐
            String salt = RandomStringUtils.randomAlphanumeric(20);
            //将密码使用sha256加密
            sysUser.setPassword(ShiroUtils.sha256(sysUser.getPassword(),salt));
            sysUser.setSalt(salt);
            //保存系统用户，保存成功后会返回主键的值
            insertSelective(sysUser);
            //赋值ID给传入参数，方便外层调用方法
            sysUserExt.setId(sysUser.getId());
            if(ConversionUtil.isNotEmptyParameter(sysUserExt.getRoleIdList())){
                //保存用户与角色关系
                sysUserRoleService.save(sysUser.getId(),sysUserExt.getRoleIdList());
            }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return WebResult.ok("创建成功");
    }

    @Transactional
    @Override
    public WebResult update(SysUserExt sysUserExt) {
        //判断要新增的用户的用户名是否已经存在
        SysUser existUser = getUserByUserName(sysUserExt.getUsername());
        //修改的不是同一个用户
        if(existUser != null && existUser.getId() != sysUserExt.getId()){
            return WebResult.fail("该用户已经存在！");
        }
        SysUser sysUser = new SysUser();
        try {
            if(StringUtils.isBlank(sysUserExt.getPassword())){
                sysUserExt.setPassword(null);
            }else{
                //修改密码的同时，把用于密码加密的盐也一起修改
                //生成20位长度的随机数，用作密码加密的盐
                String salt = RandomStringUtils.randomAlphanumeric(20);
                //将密码使用sha256加密
                sysUserExt.setPassword(ShiroUtils.sha256(sysUserExt.getPassword(),salt));
                sysUserExt.setSalt(salt);
            }
            //相同属性复制
            BeanUtils.copyProperties(sysUser,sysUserExt);
            sysUser.setUpdateTime(new Date());
            //修改系统用户
            updateByPrimaryKeySelective(sysUser);
            if(ConversionUtil.isNotEmptyParameter(sysUserExt.getRoleIdList())){
                //保存用户与角色关系
                sysUserRoleService.save(sysUser.getId(),sysUserExt.getRoleIdList());
            }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return WebResult.ok("修改成功");
    }

    @Transactional //开启事务（涉及到多表操作）
    @Override
    public void delete(Long deleteUserId, List<Long> idList) {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andIdIn(idList);
        SysUser sysUser = new SysUser();
        sysUser.setDeleteFlag(Constants.DELETE_FLAG.YES);
        sysUser.setDeleteUser(deleteUserId);
        sysUser.setDeleteTime(new Date());
        updateByExampleSelective(sysUser,userExample);//逻辑删除

        //删除用户与角色关系
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdIn(idList);
        sysUserRoleService.deleteByExample(userRoleExample);
    }

    @Override
    public WebResult login(LoginVo vo) {
        //根据用户名查找用户
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria()
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO)
                .andUsernameEqualTo(vo.getUsername())
                .andPlatformEqualTo(vo.getPlatform());
        List<SysUser> sysUserList = selectByExample(userExample);
        if(CollectionUtils.isEmpty(sysUserList)){
            return WebResult.fail("用户名或者密码不正确");
        }
        SysUser sysUser = sysUserList.get(0);
        String password = ShiroUtils.sha256(vo.getPassword(),sysUser.getSalt());
        //密码错误
        if(!password.equals(sysUser.getPassword())) {
            return WebResult.fail("用户名或者密码不正确");
        }
        //账号已经被禁用
        if(sysUser.getStatus()== SysConstants.USER_STATUS.FROZEN){
            return WebResult.fail("账号已被锁定,请联系管理员");
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
        Set<String> permsSet = sysResourceService.getUserPerms(sysUser.getId(),vo.getPlatform());
        sysLoginUserInfo.setPerms(permsSet);
        //获取用户角色集合
        List<Long> roleList = sysUserRoleService.getRoleIdList(sysUser.getId());
        sysLoginUserInfo.setRoleIds(roleList);

        //将登录信息缓存到redis
       // CacheService.setCacheObj(redisTemplate,token,sysLoginUserInfo);
        managerCacheService.saveLoginUserInfo(token,sysLoginUserInfo);
        //登录日志,使用stream
        SysLoginMsg msg = new SysLoginMsg();
        msg.setOperationSystem(vo.getOperationSystem());
        msg.setBrowser(vo.getBrowser());
        msg.setAddress(vo.getAddress());
        msg.setPlatform(vo.getPlatform());
        msg.setSysUserId(String.valueOf(sysUser.getId()));
        msg.setLoginTime(new Date());
        logService.login(msg);

        return WebResult.ok().put("token",token).put("perms",permsSet);
    }

    @Override
    public SysUser getUserByUserName(String username) {
        //根据用户名查找用户
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria()
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO)
                .andUsernameEqualTo(username);
        List<SysUser> sysUserList = selectByExample(userExample);
        if(CollectionUtils.isNotEmpty(sysUserList)){
            return sysUserList.get(0);
        }
        return null;
    }

    @Override
    public boolean checkUserName(String userName) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(userName)
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<SysUser> sysUsers = this.selectByExample(example);
        if(ConversionUtil.isNotEmptyParameter(sysUsers)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUserName(String userName, Long id) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(userName)
                .andIdNotEqualTo(id)
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<SysUser> sysUsers = this.selectByExample(example);
        if(ConversionUtil.isNotEmptyParameter(sysUsers)){
            return true;
        }
        return false;
    }
    @Override
    public List<SysUser> getByOrgId(String orgId){
        SysUserExample example = new SysUserExample();
        example.createCriteria()
                .andOrgIdEqualTo(orgId)
                .andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<SysUser> sysUsers = this.selectByExample(example);
        return sysUsers;
    }

    /**
     *
     * @param orgId
     * @return key:id,  value:userName
     */
    @Override
    public Map<String, String> getUserNameMapByOrgId(String orgId){
        List<SysUser> userList = getByOrgId(orgId);
        //list转map
        return userList.stream().collect(Collectors.toMap(o -> String.valueOf(o.getId()),SysUser::getUsername));
    }
}