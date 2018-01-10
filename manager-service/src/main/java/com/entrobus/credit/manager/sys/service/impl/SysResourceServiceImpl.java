package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.manager.common.SysConstants;
import com.entrobus.credit.manager.common.bean.SysMenu;
import com.entrobus.credit.manager.common.bean.SysResourceVo;
import com.entrobus.credit.manager.common.bean.ZtreeMenuVo;
import com.entrobus.credit.manager.dao.SysResourceMapper;
import com.entrobus.credit.manager.sys.service.SysResourceService;
import com.entrobus.credit.manager.sys.service.SysRoleResourceService;
import com.entrobus.credit.manager.sys.service.SysUserRoleService;
import com.entrobus.credit.pojo.manager.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class SysResourceServiceImpl implements SysResourceService {
    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    private static final Logger logger = LoggerFactory.getLogger(SysResourceServiceImpl.class);

    public int countByExample(SysResourceExample example) {
        int count = this.sysResourceMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public SysResource selectByPrimaryKey(Long id) {
        return this.sysResourceMapper.selectByPrimaryKey(id);
    }

    public List<SysResource> selectByExample(SysResourceExample example) {
        return this.sysResourceMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Long id) {
        return this.sysResourceMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysResource record) {
        return this.sysResourceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysResource record) {
        return this.sysResourceMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(SysResourceExample example) {
        return this.sysResourceMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(SysResource record, SysResourceExample example) {
        return this.sysResourceMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(SysResource record, SysResourceExample example) {
        return this.sysResourceMapper.updateByExample(record, example);
    }

    public int insert(SysResource record) {
        return this.sysResourceMapper.insert(record);
    }

    public int insertSelective(SysResource record) {
        return this.sysResourceMapper.insertSelective(record);
    }

    @Transactional//开启事务（涉及到多表操作）
    @Override
    public void delete(Long loginUserId, List<Long> idList) {
        SysResourceExample example = new SysResourceExample();
        List<Long> sysSubResourceIdList = new ArrayList<>();
        for(Long id : idList){
            //查询这个ID下的所有子资源
            example.createCriteria()
                    .andDeleteFlagEqualTo(Constants.DeleteFlag.NO)
                    .andParentIdsLike("%" + id + "%");
            //查询子资源
            List<SysResource> sysSubResourceList = selectByExample(example);
            if(CollectionUtils.isNotEmpty(sysSubResourceList)){
                for(SysResource sysSubResource : sysSubResourceList){
                    //将需要删除的子资源的id也加入到idList集合中
                    sysSubResourceIdList.add(sysSubResource.getId());
                }
            }
        }
        if(CollectionUtils.isNotEmpty(sysSubResourceIdList)){
            idList.addAll(sysSubResourceIdList);
        }
        //1.删除资源
        SysResource sysResource = new SysResource();
        sysResource.setDeleteTime(new Date());
        sysResource.setDeleteUser(loginUserId);
        sysResource.setDeleteFlag(Constants.DeleteFlag.YES);
        example = new SysResourceExample();
        example.createCriteria().andIdIn(idList);//要删除的资源ID
        updateByExampleSelective(sysResource,example);//逻辑删除

        //2.删除资源和角色的关系
        SysRoleResourceExample roleResourceExample = new SysRoleResourceExample();
        roleResourceExample.createCriteria().andResourceIdIn(idList);
        sysRoleResourceService.deleteByExample(roleResourceExample);//物理删除
    }

    @Override
    public SysResourceVo getResourceTreeById(Long id) {
        //1.根据ID获取资源以及这个ID下的所有子资源
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO).andIdEqualTo(id);
        //2.构建查询这个ID下的所有子资源的查询条件
        SysResourceExample.Criteria criteria2 = example.createCriteria();
        criteria2
                .andDeleteFlagEqualTo(Constants.DeleteFlag.NO)
                .andParentIdsLike("%" + id + "%");
        example.or(criteria2);//example多条件拼接查询
        // 原始的数据
        List<SysResource> sysResourceList = selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysResourceList)) {
            //生成节点树
            return generateTreeNode(id, sysResourceList);
        }
        return null;
    }

    @Override
    public List<SysResourceVo> getResourceTreeList() {
        List<SysResourceVo> sysResourceVoList = new ArrayList<>();
        //查询出所有未删除的资源
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        // 原始的数据
        List<SysResource> sysResourceList = selectByExample(example);
        if(CollectionUtils.isNotEmpty(sysResourceList)){
            //一级节点（根节点集合）
            List<SysResource> rootNodeList = new ArrayList<>();
            for(SysResource resource:sysResourceList){
                //获取根节点集合(根节点没有parentId)
                if(resource.getParentId() == null){
                    rootNodeList.add(resource);
                }
            }
            for(SysResource resource:rootNodeList){
                //生成每个根节点的节点树
                SysResourceVo resourceVo = generateTreeNode(resource.getId(),sysResourceList);
                sysResourceVoList.add(resourceVo);
            }
        }

        return sysResourceVoList;
    }

    @Override
    public List<ZtreeMenuVo> getZtreeMenu(Integer menuType,String filterResourceUrls) {
        List<ZtreeMenuVo> menuVoList = new ArrayList<>();
        //1.查询未删除的菜单
        SysResourceExample resourceExample = new SysResourceExample();
        SysResourceExample.Criteria criteria = resourceExample.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        if(menuType != null){
            criteria.andTypeEqualTo(menuType);
        }
        List<SysResource> sysResourceList = selectByExample(resourceExample);
        if(CollectionUtils.isNotEmpty(sysResourceList)){
            for(SysResource resource : sysResourceList){
                //如果该资源不是要过滤的资源，就添加到集合中
                if(!filterResourceUrls.contains(resource.getUrl())){
                    ZtreeMenuVo ztreeMenuVo = new ZtreeMenuVo();
                    ztreeMenuVo.setId(resource.getId());
                    ztreeMenuVo.setpId(resource.getParentId());
                    ztreeMenuVo.setName(resource.getName());
                    ztreeMenuVo.setMenuLevel(resource.getLevel());
                    menuVoList.add(ztreeMenuVo);
                }
            }
        }
        return menuVoList;
    }

    @Override
    public List<ZtreeMenuVo> getCheckTreeList(Long roleId,String filterResourceUrls) {
        List<ZtreeMenuVo> menuVoList = getZtreeMenu(null,filterResourceUrls);
        //获取该角色拥有的资源
        SysRoleResourceExample roleResourceExample = new SysRoleResourceExample();
        roleResourceExample.createCriteria().andRoleIdEqualTo(roleId);
        List<SysRoleResource> roleResourceList = sysRoleResourceService.selectByExample(roleResourceExample);
        if(CollectionUtils.isNotEmpty(roleResourceList)){
            for(ZtreeMenuVo ztreeMenuVo:menuVoList){
                for(SysRoleResource roleResource:roleResourceList){
                    if(ztreeMenuVo.getId() == roleResource.getResourceId()){
                        ztreeMenuVo.setChecked(true);//选中
                    }
                }
            }
        }
        return menuVoList;
    }

    @Override
    public List<SysResource> getSysResourceByUserId(Long userId,Integer platform,Integer type) {
        List<SysResource> sysResourceList = new ArrayList<>();
        //1.查找该用户所属的角色
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<SysUserRole> sysUserRoleList = sysUserRoleService.selectByExample(userRoleExample);
        if(CollectionUtils.isNotEmpty(sysUserRoleList)){
            List<Long> roleIdList = new ArrayList<>();
            for(SysUserRole userRole:sysUserRoleList){
                roleIdList.add(userRole.getRoleId());
            }
            //2.查找角色所能访问的资源
            SysRoleResourceExample roleResourceExample = new SysRoleResourceExample();
            roleResourceExample.createCriteria().andRoleIdIn(roleIdList);
            List<SysRoleResource> roleResourceList = sysRoleResourceService.selectByExample(roleResourceExample);
            if(CollectionUtils.isNotEmpty(roleResourceList)){
                List<Long> resourceIdList = new ArrayList<>();
                for(SysRoleResource roleResource:roleResourceList){
                    resourceIdList.add(roleResource.getResourceId());
                }
                //3.根据资源ID查找资源信息
                SysResourceExample sysResourceExample = new SysResourceExample();
                SysResourceExample.Criteria criteria = sysResourceExample.createCriteria();
                criteria.andIdIn(resourceIdList).andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
                if(platform != null){
                    criteria.andPlatformEqualTo(platform);
                }
                if(type != null){
                    criteria.andTypeEqualTo(type);
                }
                sysResourceExample.setOrderByClause(" order_num asc , create_time desc ");
                sysResourceList = selectByExample(sysResourceExample);
            }
        }
        return sysResourceList;
    }

    @Override
    public List<SysMenu> getNavMenu(Long userId, Integer platform) {
        List<SysMenu> sysMenuList = new ArrayList<>();
        List<SysResource> sysResourceList = getSysResourceByUserId(userId,platform, SysConstants.RESOURCE_TYPE.MENU);
        if(CollectionUtils.isNotEmpty(sysResourceList)){
            //找出一级菜单
            List<SysResource> firstLevelMenuList = new ArrayList<>();
            for(SysResource resource : sysResourceList){
                if(resource.getParentId() == null){
                    firstLevelMenuList.add(resource);
                }
            }

            for(SysResource resource : firstLevelMenuList){
                SysMenu sysMenu = generateNavMenu(resource.getId(),sysResourceList);
                sysMenuList.add(sysMenu);
            }
        }
        return sysMenuList;
    }

    @Override
    public Set<String> getUserPerms(Long userId, Integer platform) {
        Set<String> permsSet = new HashSet<>();
        List<SysResource> sysResourceList = getSysResourceByUserId(userId,platform,null);
        if(CollectionUtils.isNotEmpty(sysResourceList)){
            for(SysResource resource : sysResourceList){
                if(StringUtils.isBlank(resource.getPerms())){
                    continue;
                }
                permsSet.addAll(Arrays.asList(resource.getPerms().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 递归生成导航菜单
     * @param rootId 根节点ID
     * @return SysMenu
     */
    private SysMenu generateNavMenu(Long rootId, List<SysResource> sysResourceList){
        //获取根菜单
        SysMenu menu = this.getMenuById(rootId, sysResourceList);
        //获取根菜单下的子菜单
        List<SysMenu> subMenuList = this.getChildrenMenuById(rootId, sysResourceList);
        for (SysMenu item : subMenuList) {
            //递归查找子菜单下的子菜单
            SysMenu node = this.generateNavMenu(item.getId(), sysResourceList);
            menu.getChildMenus().add(node);
        }
        return menu;
    }

    /**
     * 获取SidebarMenu
     * @param menuId 菜单ID
     * @return SidebarMenu
     */
    private SysMenu getMenuById(Long menuId,List<SysResource> sysResourceList){
        SysMenu menu = new SysMenu();
        for(SysResource resource : sysResourceList){
            if(resource.getId() == menuId){
                menu.setId(resource.getId());
                menu.setIcon(resource.getIcon());
                menu.setMenuName(resource.getName());
                menu.setHref(resource.getUrl());
                menu.setPermission(resource.getPerms());
                menu.setParentId(resource.getParentId());
                break;
            }
        }
        return menu;
    }

    /**
     * 获取指定菜单下的子菜单
     * @param menuId 菜单ID
     * @return childrenTreeNode 子节点集合
     */
    private List<SysMenu> getChildrenMenuById(Long menuId,List<SysResource> sysResourceList){
        List<SysMenu> subMenuList = new ArrayList<>();
        for(SysResource resource : sysResourceList){
            SysMenu menu = new SysMenu();
            if(resource.getParentId() == menuId){
                menu.setId(resource.getId());
                menu.setIcon(resource.getIcon());
                menu.setMenuName(resource.getName());
                menu.setHref(resource.getUrl());
                menu.setPermission(resource.getPerms());
                menu.setParentId(resource.getParentId());
                subMenuList.add(menu);
            }
        }
        return subMenuList;
    }

    /**
     *寻找指定ID的子节点
     * @param nodeId 节点ID
     * @return childrenTreeNode 子节点集合
     */
    private List<SysResourceVo> getChildrenNodeById(Long nodeId,List<SysResource> sysResourceList){
        List<SysResourceVo> childrenTreeNode = new ArrayList<>();
        for(SysResource resource : sysResourceList){
            SysResourceVo sysResourceVo = new SysResourceVo();
            if(resource.getParentId() == nodeId){
                try {
                    //相同属性复制
                    BeanUtils.copyProperties(sysResourceVo,resource);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                childrenTreeNode.add(sysResourceVo);
            }
        }
        return childrenTreeNode;
    }

    /**
     * 递归生成Tree结构数据
     * @param rootId 根节点ID
     * @return SysResourceVo
     */
    private SysResourceVo generateTreeNode(Long rootId,List<SysResource> sysResourceList){
        SysResourceVo root = this.getNodeById(rootId,sysResourceList);
        List<SysResourceVo> childrenTreeNode = this.getChildrenNodeById(rootId,sysResourceList);
        for (SysResourceVo item : childrenTreeNode) {
            //递归查找子节点
            SysResourceVo node = this.generateTreeNode(item.getId(),sysResourceList);
            root.getChildsList().add(node);
        }
        return root;
    }

    /**
     * 寻找指定ID的节点
     * @param nodeId 节点ID
     * @return treeNode
     */
    private SysResourceVo getNodeById(Long nodeId,List<SysResource> sysResourceList){
        SysResourceVo treeNode = new SysResourceVo();
        for(SysResource resource : sysResourceList){
            if(resource.getId() == nodeId){
                try {
                    //相同属性复制
                    BeanUtils.copyProperties(treeNode,resource);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return treeNode;
    }
}