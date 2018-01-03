package com.entrobus.credit.manager.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单VO
 * Created by gacl on 2017/3/21.
 */
public class SysMenu implements Serializable {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 名称
     */
    private String menuName;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 链接地址
     */
    private String href;

    /**
     * 图标名称
     */
    private String icon;

    /** 子菜单List */
    private List<SysMenu> childMenus = new ArrayList<>();//子菜单;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SysMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<SysMenu> childMenus) {
        this.childMenus = childMenus;
    }
}
