package com.entrobus.credit.manager.sys.controller;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.Constants;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.sys.service.SysUserService;
import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.pojo.manager.SysUserExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends ManagerBaseController {

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/add")
    public WebResult add(SysUserExt sysUser) {
        sysUser.setCreateUser(getLoginUserId());//创建人的用户ID
        sysUser.setUpdateUser(getLoginUserId());//最近一次修改的用户ID
        sysUserService.save(sysUser);
        return WebResult.ok("创建成功！");
    }

    /**
     * 获取系统用户列表
     * @return
     */
    @RequestMapping("/list")
    public WebResult list(Integer offset, Integer limit,String username) {
        if (offset != null && limit != null) {
            //分页查询
            PageHelper.offsetPage(offset, limit);
        }
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(com.entrobus.credit.common.Constants.DeleteFlag.NO);
        //只有超级管理员，才能查看所有管理员列表
        if (getLoginUserId() != Constants.SUPER_ADMIN) {
            criteria.andCreateUserEqualTo(getLoginUserId());
        }
        if(StringUtils.isNotEmpty(username)){
            criteria.andUsernameLike("%"+username+"%");
        }
        //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页。
        List<SysUser> sysUserList = sysUserService.selectByExample(example);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total",pageInfo.getTotal());
        dataMap.put("rows", pageInfo.getList());
        //服务端分页，返回的结果必须包含total、rows两个参数。漏写或错写都会导致表格无法显示数据。
        return WebResult.ok(dataMap);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login")
    public WebResult login(String username, String password){
        //用户登录
        String token = sysUserService.login(username,password);
        return WebResult.ok().put("token",token);
    }

    /**
     * 获取登录用户信息
     */
    @RequestMapping(value = "/getLoginUserInfo")
    public WebResult getLoginUserInfo(){
        //用户登录
        SysLoginUserInfo loginUserInfo = getCurrLoginUser();
        return WebResult.ok().put("loginUser",loginUserInfo);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout")
    public WebResult logout(String token) {
        //删除缓存中的用户信息
        CacheService.delete(redisTemplate,token);
        return WebResult.ok();
    }

    /**
     * 更新系统用户
     * @param sysUser
     * @return
     */
    @PostMapping("/update")
    public WebResult update(SysUserExt sysUser){
        if(StringUtils.isBlank(sysUser.getUsername())){
            return WebResult.error("用户名不能为空");
        }
        sysUser.setUpdateUser(getLoginUserId());
        sysUserService.update(sysUser);
        return WebResult.ok("修改成功");
    }

    /**
     * 根据用户id批量删除用户
     * @param ids 要删除的用户ID，以逗号分隔
     * @return
     */
    @RequestMapping(value = "/delete")
    public WebResult delete(String ids){
        List<Long> idList = new ArrayList<>();
        if(StringUtils.isNotEmpty(ids)){
            String[] idArr = ids.split(",");
            for(String id:idArr){
                idList.add(Long.parseLong(id));
            }
        }
        if(idList.contains(Constants.SUPER_ADMIN)){
            return WebResult.error("系统管理员不能删除");
        }
        if(idList.contains(getLoginUserId())){
            return WebResult.error("当前登录用户不能删除");
        }
        //根据用户id删除
        sysUserService.delete(getLoginUserId(),idList);
        return WebResult.ok();
    }
}
