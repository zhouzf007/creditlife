package com.entrobus.credit.manager.common.bean;


import com.entrobus.credit.pojo.manager.SysResource;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统资源Vo，树形结构展示
 * Created by gacl on 2017/12/4.
 */
public class SysResourceVo extends SysResource {

    private List<SysResourceVo> childsList=new ArrayList<SysResourceVo>();

    public List<SysResourceVo> getChildsList() {
        return childsList;
    }

    public void setChildsList(List<SysResourceVo> childsList) {
        this.childsList = childsList;
    }
}
