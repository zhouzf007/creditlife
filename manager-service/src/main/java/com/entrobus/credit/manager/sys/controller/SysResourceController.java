package com.entrobus.credit.manager.sys.controller;

import com.alibaba.fastjson.JSONArray;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.manager.common.bean.CommonParameter;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.bean.SysMenu;
import com.entrobus.credit.manager.common.bean.ZtreeMenuVo;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.sys.service.SysResourceService;
import com.entrobus.credit.pojo.manager.SysResource;
import com.entrobus.credit.pojo.manager.SysResourceExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 系统资源Controller
 */
@RestController
@RequestMapping("/sys/resource")
public class SysResourceController extends ManagerBaseController {

    @Autowired
    private SysResourceService sysResourceService;

    @RequestMapping("/add")
    public WebResult add(SysResource sysResource,CommonParameter commonParameter){
        sysResource.setCreateUser(getLoginUserId());
        sysResource.setUpdateUser(getLoginUserId());
        sysResource.setCreateTime(new Date());
        sysResource.setDeleteFlag(Constants.DeleteFlag.NO);
        sysResource.setPlatform(commonParameter.getPlatform());
        if(ConversionUtil.isEmptyParameter(sysResource.getLevel())){
            sysResource.setLevel(1);
        }
        sysResourceService.insertSelective(sysResource);
        return WebResult.ok("创建成功！");
    }

    @RequestMapping("/update")
    public WebResult update(SysResource sysResource,CommonParameter commonParameter){
        sysResource.setUpdateUser(getLoginUserId());
        sysResource.setUpdateTime(new Date());
        sysResource.setPlatform(commonParameter.getPlatform());
        if(ConversionUtil.isEmptyParameter(sysResource.getLevel())){
            sysResource.setLevel(1);
        }
        sysResourceService.updateByPrimaryKeySelective(sysResource);
        return WebResult.ok("修改成功！");
    }

    /**
     * 根据资源id批量删除资源
     * @param ids 要删除的资源ID，以逗号分隔
     * @return
     */
    @RequestMapping(value = "/delete")
    public WebResult delete(String ids){
        List<Long> idList = new ArrayList<>();
        if(ConversionUtil.isNotEmptyParameter(ids)){
            idList = JSONArray.parseArray(ids,Long.class);
        }
        //根据资源id删除
        sysResourceService.delete(getLoginUserId(), idList);
        return WebResult.ok("删除成功");
    }
    /**
     * 根据资源ID获取资源信息
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public WebResult info(@PathVariable("id") Long id) {
        return WebResult.ok(sysResourceService.selectByPrimaryKey(id));
    }

    /**
     * 分页查询系统资源
     * @param offset 从第几条开始取
     * @param limit 每次取多少条
     * @param name 资源名称
     * @return
     */
    @RequestMapping("/list")
    public WebResult list(Integer offset, Integer limit,String name,Long id,CommonParameter commonParameter){
        if (offset != null && limit != null) {
            //分页查询
            PageHelper.offsetPage(offset, limit);
        }
        SysResourceExample resourceExample = new SysResourceExample();
        SysResourceExample.Criteria criteria = resourceExample.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        criteria.andPlatformEqualTo(commonParameter.getPlatform());
        if(StringUtils.isNotEmpty(name)){
            criteria.andNameLike("%"+name+"%");
            //把该name下的子资源一起查询出来
            resourceExample.or().andParentNamesLike("%" + name + "%");
        }
        if(id != null){
            criteria.andIdEqualTo(id);
            //把该Id下的子资源一起查询出来
            resourceExample.or().andParentIdsLike("%"+id+"%");
        }

        List<SysResource> sysResourceList = sysResourceService.selectByExample(resourceExample);
        //分页信息
        PageInfo<SysResource> pageInfo = new PageInfo(sysResourceList);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("total",pageInfo.getTotal());
        dataMap.put("rows", pageInfo.getList());
        //服务端分页，返回的结果必须包含total、rows两个参数。漏写或错写都会导致表格无法显示数据。
        return WebResult.ok(dataMap);
    }

    /**
     * 根据资源ID获取资源信息以及子资源信息
     * @param id
     * @return
     */
    @RequestMapping("/getResourceTreeById")
    public WebResult getResourceTreeById(Long id) {
        return WebResult.ok(sysResourceService.getResourceTreeById(id));
    }

    /**
     * 获取资源树集合
     * @return
     */
    @RequestMapping("/getResourceTreeList")
    public WebResult getResourceTreeList() {
        return WebResult.ok(sysResourceService.getResourceTreeList());
    }

    /**
     * 获取导航菜单
     * @return
     */
    @RequestMapping("/navMenu")
    public WebResult getNavMenu(){
        SysLoginUserInfo loginUserInfo = getCurrLoginUser();
        List<SysMenu> sysMenuList = sysResourceService.getNavMenu(loginUserInfo.getId(),loginUserInfo.getPlatform());
        return WebResult.ok(sysMenuList);
    }

    /**
     * 获取菜单树形列表
     * @return
     */
    @RequestMapping("/treeList")
    public WebResult treeList(Integer menuType, CommonParameter commonParameter,String filterResourceUrls) {
        List<ZtreeMenuVo> menuVoList = sysResourceService.getZtreeMenu(menuType,commonParameter.getPlatform());
        return WebResult.ok(menuVoList);
    }

    /**
     * 根据角色ID获取菜单树形列表
     * @return
     */
    @RequestMapping("/checkTreeList")
    public WebResult checkTreeList(Long roleId,CommonParameter commonParameter,String filterResourceUrls) {
        List<ZtreeMenuVo> menuVoList = sysResourceService.getCheckTreeList(roleId,commonParameter.getPlatform());
        return WebResult.ok(menuVoList);
    }
}
