package com.entrobus.credit.manager.sys.controller;

import com.alibaba.fastjson.JSONArray;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.annotation.RecordLog;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.manager.common.bean.CommonParameter;
import com.entrobus.credit.manager.common.bean.SysRoleExt;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.sys.service.SysRoleResourceService;
import com.entrobus.credit.manager.sys.service.SysRoleService;
import com.entrobus.credit.manager.sys.service.SysUserService;
import com.entrobus.credit.pojo.manager.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统角色Controller
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends ManagerBaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/add")
    @RecordLog(desc = "新增管理员角色")
    public WebResult add(SysRoleExt role,CommonParameter commonParameter){
        role.setCreateUser(getLoginUserId());
        role.setUpdateUser(getLoginUserId());//最近一次修改的用户ID
        role.setOrgId(getCurrLoginUser().getOrgId());//关联组织ID
        role.setPlatform(commonParameter.getPlatform());
        sysRoleService.save(role);
        return WebResult.ok("添加成功");
    }


    @RequestMapping("/update")
    @RecordLog(desc = "修改管理员角色")
    public WebResult update(SysRoleExt role,CommonParameter commonParameter){
        role.setUpdateUser(getLoginUserId());//最近一次修改的用户ID
        role.setPlatform(commonParameter.getPlatform());
        sysRoleService.update(role);
        return WebResult.ok();
    }

    /**
     * 根据用户id批量删除角色
     * @param ids 要删除的资源ID，以逗号分隔
     * @return
     */
    @RequestMapping(value = "/delete")
    public WebResult delete(String ids){
        List<Long> idList = new ArrayList<>();
        if(ConversionUtil.isNotEmptyParameter(ids)){
            idList = JSONArray.parseArray(ids,Long.class);
        }
        //根据角色id删除
        sysRoleService.delete(getLoginUserId(),idList);
        return WebResult.ok();
    }

    /**
     * 根据角色ID获取角色信息
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public WebResult info(@PathVariable("id") Long id) {
        SysRole sysRole = sysRoleService.selectByPrimaryKey(id);
        if(sysRole == null){
            return WebResult.ok();
        }
        SysRoleExt sysRoleExt = new SysRoleExt();
        try {
            BeanUtils.copyProperties(sysRoleExt,sysRole);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
        //获取角色所拥有的资源列表
        SysRoleResourceExample roleResourceExample = new SysRoleResourceExample();
        roleResourceExample.createCriteria().andRoleIdEqualTo(id);
        List<SysRoleResource> roleResourceList = sysRoleResourceService.selectByExample(roleResourceExample);
        if(CollectionUtils.isNotEmpty(roleResourceList)){
            List<Long> resourceIdList = new ArrayList<>();
            for(SysRoleResource roleResource : roleResourceList){
                resourceIdList.add(roleResource.getId());
            }
            sysRoleExt.setResourceIdList(resourceIdList);
        }
        return WebResult.ok(sysRoleExt);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    public WebResult select(CommonParameter commonParameter){
        SysRoleExample roleExample = new SysRoleExample();
        SysRoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andPlatformEqualTo(commonParameter.getPlatform());
        criteria.andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);//未删除(待优化，后期改成使用mybatis拦截器统一处理带delete_flag过滤条件的查询)
        if(commonParameter.getPlatform()==Constants.PLATFORM.BANK){
            criteria.andOrgIdEqualTo(getCurrLoginUser().getOrgId());
        }
        //只有超级管理员，才能查看所有管理员列表
       /* if (!getCurrLoginUser().getRoleIds().contains(SysConstants.LOGIN_USER_ROLE.SUPER_ADMIN)) {
            criteria.andCreateUserEqualTo(getLoginUserId());
        }*/
        List<SysRole> list = sysRoleService.selectByExample(roleExample);
        return WebResult.ok(list);
    }

    /**
     * 获取系统角色列表
     * @return
     */
    @RequestMapping("/list")
    public WebResult list(Integer offset, Integer limit,String roleName,CommonParameter commonParameter) {
        if (offset != null && limit != null) {
            //分页查询
            PageHelper.offsetPage(offset, limit);
        }
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);//未删除
        if(commonParameter.getPlatform()==Constants.PLATFORM.BANK){
            criteria.andOrgIdEqualTo(getCurrLoginUser().getOrgId());
        }
        if(StringUtils.isNotEmpty(roleName)){
            criteria.andRoleNameLike("%"+roleName+"%");
        }
        criteria.andPlatformEqualTo(commonParameter.getPlatform());
        //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页。
        List<SysRole> sysRoleList = sysRoleService.selectByExample(example);
        List<Map<String,Object>> resultList = new ArrayList<>();

        for (SysRole role : sysRoleList){
            Map<String,Object> map = ConversionUtil.beanToMap(role);
            SysUser sysUser = sysUserService.selectByPrimaryKey(role.getUpdateUser());
            if(ConversionUtil.isNotEmptyParameter(sysUser)){
                map.put("updateUserName",sysUser.getRealName());
            }
            map.put("strUpdateTime",ConversionUtil.isEmptyParameter(role.getUpdateTime()) ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(role.getUpdateTime()));
            resultList.add(map);
        }
        PageInfo pageInfo = new PageInfo<>(sysRoleList);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total",pageInfo.getTotal());
        dataMap.put("rows", resultList);
        //服务端分页，返回的结果必须包含total、rows两个参数。漏写或错写都会导致表格无法显示数据。
        return WebResult.ok(dataMap);
    }
}
