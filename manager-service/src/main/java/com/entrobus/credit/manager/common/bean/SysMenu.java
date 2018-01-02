package com.entrobus.credit.manager.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单VO
 * Created by gacl on 2017/3/21.
 */
public class SysMenu implements Serializable {

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 类型：1.菜单；2.功能；3.子功能；0.操作
     */
    private Integer menuType;

    /**
     * 权限名称
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
    private List<SysMenu> childMenus;

    /** 是否选中 */
    private boolean checked;

    public List<SysMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<SysMenu> childMenus) {
        this.childMenus = childMenus;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
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
}
