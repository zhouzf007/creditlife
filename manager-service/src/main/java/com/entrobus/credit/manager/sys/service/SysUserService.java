package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.bean.LoginVo;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.pojo.manager.SysUserExample;
import com.entrobus.credit.vo.log.SysLoginMsg;

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
    WebResult login(LoginVo vo) ;

    /**
     * 检查对应平台账号是否重复 重复=true
     * @param userName
     * @param platform
     * @return
     */
    boolean checkUserName(String userName,Integer platform);

    /**
     * 通过username获取用户
     * @param username
     * @return
     */
    SysUser getUserByUserName(String username);
}