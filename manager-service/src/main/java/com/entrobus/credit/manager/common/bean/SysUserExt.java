package com.entrobus.credit.manager.common.bean;


import com.entrobus.credit.pojo.manager.SysUser;

import java.util.List;

/**
 * 系统用户扩展
 */
public class SysUserExt extends SysUser {

    private String roleIds;

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
