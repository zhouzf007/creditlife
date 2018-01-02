package com.entrobus.credit.manager.common.bean;


import com.entrobus.credit.pojo.manager.SysRole;

import java.util.List;

/**
 * 系统角色扩展
 */
public class SysRoleExt extends SysRole {

    private String resourceIds;

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * 角色对应的系统资源Id(菜单ID，按钮ID)
     */
    private List<Long> resourceIdList;

    public List<Long> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Long> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
