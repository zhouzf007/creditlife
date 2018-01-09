package com.entrobus.credit.manager.sys.controller;

import com.alibaba.fastjson.JSONArray;
import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.manager.common.SysConstants;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.sys.service.SysUserRoleService;
import com.entrobus.credit.manager.sys.service.SysUserService;
import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.pojo.manager.SysUserExample;
import com.entrobus.credit.pojo.manager.SysUserRole;
import com.entrobus.credit.pojo.manager.SysUserRoleExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends ManagerBaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;


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
    public WebResult list(Integer offset, Integer limit,String username,String realName,String cellphone,Integer platform) {
        if (offset != null && limit != null) {
            //分页查询
            PageHelper.offsetPage(offset, limit);
        }
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(com.entrobus.credit.common.Constants.DeleteFlag.NO);
        //只有超级管理员，才能查看所有管理员列表
        if (!getCurrLoginUser().getRoleIds().contains(SysConstants.LOGIN_USER_ROLE.SUPER_ADMIN)) {
            criteria.andCreateUserEqualTo(getLoginUserId());
        }
        if(StringUtils.isNotEmpty(username)){
            criteria.andUsernameLike("%"+username+"%");
        }
        if(StringUtils.isNotEmpty(realName)){
            criteria.andRealNameLike("%"+realName+"%");
        }
        if(StringUtils.isNotEmpty(cellphone)){
            criteria.andCellphoneLike("%"+cellphone+"%");
        }
        if(ConversionUtil.isNotEmptyParameter(platform)){
            criteria.andPlatformEqualTo(platform);
        }
        //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页。
        List<SysUser> sysUserList = sysUserService.selectByExample(example);
        List<Map<String,Object>> resultList = new ArrayList<>();
        //角色集合
        List<Long> sysUserIdList = new ArrayList<>();
        for (SysUser sysUser: sysUserList){

            sysUserIdList.add(sysUser.getId());
        }
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdIn(sysUserIdList);
        List<SysUserRole> roleList = sysUserRoleService.selectByExample(sysUserRoleExample);
        for (SysUser sysUser: sysUserList){
            Map<String,Object> map = ConversionUtil.beanToMap(sysUser);
            List<Long> roleIdList = new ArrayList<>();
            for (SysUserRole role: roleList){
                if(role.getUserId().equals(sysUser.getId())){
                    roleIdList.add(role.getRoleId());
                }
            }
            map.put("roleIdList",roleIdList);
            resultList.add(map);
        }
        PageInfo pageInfo = new PageInfo<>(resultList);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total",pageInfo.getTotal());
        dataMap.put("rows", resultList);
        //服务端分页，返回的结果必须包含total、rows两个参数。漏写或错写都会导致表格无法显示数据。
        return WebResult.ok(dataMap);
    }

    /**
     * 登录
     * @param platform 用户所属平台(0：信用贷后台，1：银行后台)
     */
    @RequestMapping(value = "/login")
    public WebResult login(String username, String password,Integer platform){
        //用户登录
        return sysUserService.login(username,password,platform);
    }

    /**
     * 获取登录用户信息
     */
    @RequestMapping(value = "/getLoginUserInfo")
    public WebResult getLoginUserInfo(){
        //获取登录用户信息
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
        if(ConversionUtil.isNotEmptyParameter(ids)){
            idList = JSONArray.parseArray(ids,Long.class);
        }
        if(idList.contains(getLoginUserId())){
            return WebResult.error("当前登录用户不能删除");
        }
        //根据用户id删除
        sysUserService.delete(getLoginUserId(),idList);
        return WebResult.ok();
    }
}
