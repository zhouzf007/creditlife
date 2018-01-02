package com.entrobus.credit.manager.sys.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.Constants;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/tool")
public class SysToolController extends ManagerBaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 创建超级管理员
     * @param sysUser
     * @return
     */
    @RequestMapping("/createSuperAdmin")
    public WebResult createSuperAdmin(SysUserExt sysUser){
        sysUser.setCreateUser(1L);//创建人的用户ID
        sysUser.setUpdateUser(1L);//最近一次修改的用户ID
        sysUser.setStatus(Constants.USER_STATUS.NORMAL);
        sysUserService.save(sysUser);
        return WebResult.ok("创建成功！").put("sysUser",sysUser);
    }
}
