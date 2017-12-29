package com.entrobus.credit.manager.sys.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.bean.SysUserExt;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysUserController extends ManagerBaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/add")
    public WebResult add(SysUserExt sysUser) {
        sysUser.setCreateUser(getLoginUserId());//创建人的用户ID
        sysUser.setUpdateUser(getLoginUserId());//最近一次修改的用户ID
        sysUserService.insertSelective(sysUser);
        return WebResult.ok("创建成功！");
    }
}
