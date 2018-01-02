package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.manager.common.bean.SysResourceVo;
import com.entrobus.credit.manager.common.bean.ZtreeMenuVo;
import com.entrobus.credit.pojo.manager.SysResource;
import com.entrobus.credit.pojo.manager.SysResourceExample;

import java.util.List;

public interface SysResourceService {
    int countByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(Long id);

    List<SysResource> selectByExample(SysResourceExample example);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);

    int deleteByExample(SysResourceExample example);

    int updateByExampleSelective(SysResource record, SysResourceExample example);

    int updateByExample(SysResource record, SysResourceExample example);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    void delete(Long loginUserId, List<Long> idList);

    /**
     * 通过Id获取资源树
     * @param id 资源id
     * @return SysResourceVo
     */
    SysResourceVo getResourceTreeById(Long id);

    /**
     * 通过Id获取资源树集合
     * @return List<SysResourceVo>
     */
    List<SysResourceVo> getResourceTreeList();

    /**
     * 获取针对Ztree控件使用的树形结构菜单
     * @param menuType 菜单类型（1：菜单   2：按钮）
     * @return
     */
    List<ZtreeMenuVo> getZtreeMenu(Integer menuType);

    /**
     * 根据角色ID获取菜单树形列表
     * @return
     */
    List<ZtreeMenuVo> getCheckTreeList(Long roleId);
}