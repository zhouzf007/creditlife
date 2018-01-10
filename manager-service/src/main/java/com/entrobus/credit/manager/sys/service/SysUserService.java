package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.pojo.manager.SysUserExample;

import java.util.List;

public interface SysUserService {
    int countByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long id);

    List<SysUser> selectByExample(SysUserExample example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    int deleteByExample(SysUserExample example);

    int updateByExampleSelective(SysUser record, SysUserExample example);

    int updateByExample(SysUser record, SysUserExample example);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    /**
     * 修改用户密码
     * @param sysLoginUserId
     * @param password
     * @param newPassword
     * @return
     */
    int updatePassword(Long sysLoginUserId, String password, String newPassword);

    /**
     * 保存用户
     * @param sysUserExt
     */
    WebResult save(SysUserExt sysUserExt);

    /**
     * 更新用户
     * @param sysUserExt
     */
    WebResult update(SysUserExt sysUserExt);

    /**
     * 删除
     * @param idList
     */
    void delete(Long deleteUserId,List<Long> idList);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录token
     */
    WebResult login(String username, String password,Integer platform) ;

    /**
     * 通过username获取用户
     * @param username
     * @return
     */
    SysUser getUserByUserName(String username);
}