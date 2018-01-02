package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.manager.common.bean.SysResourceVo;
import com.entrobus.credit.manager.common.bean.ZtreeMenuVo;
import com.entrobus.credit.manager.dao.SysResourceMapper;
import com.entrobus.credit.manager.sys.service.SysResourceService;
import com.entrobus.credit.manager.sys.service.SysRoleResourceService;
import com.entrobus.credit.pojo.manager.SysResource;
import com.entrobus.credit.pojo.manager.SysResourceExample;
import com.entrobus.credit.pojo.manager.SysRoleResource;
import com.entrobus.credit.pojo.manager.SysRoleResourceExample;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysResourceServiceImpl implements SysResourceService {
    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

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
    public List<ZtreeMenuVo> getZtreeMenu(Integer menuType) {
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
                ZtreeMenuVo ztreeMenuVo = new ZtreeMenuVo();
                ztreeMenuVo.setId(resource.getId());
                ztreeMenuVo.setpId(resource.getParentId());
                ztreeMenuVo.setName(resource.getName());
                ztreeMenuVo.setMenuLevel(resource.getLevel().intValue());
                menuVoList.add(ztreeMenuVo);
            }
        }
        return menuVoList;
    }

    @Override
    public List<ZtreeMenuVo> getCheckTreeList(Long roleId) {
        List<ZtreeMenuVo> menuVoList = getZtreeMenu(null);
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