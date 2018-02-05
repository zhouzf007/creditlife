package com.entrobus.credit.manager.user.controller;

import com.entrobus.credit.common.annotation.RecordLog;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.client.OrderClient;
import com.entrobus.credit.manager.client.UserClient;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.vo.order.OrderListVo;
import com.entrobus.credit.vo.order.UserOrderDtlVo;
import com.entrobus.credit.vo.order.UserOrderListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController extends ManagerBaseController {

    @Autowired
    private OrderClient orderClient;
    @Autowired
    private UserClient userClient;


    @GetMapping("/userOrderList")
    public WebResult getUserOrderList(Integer state,String key,Integer offset, Integer limit) {
        return orderClient.getUserOrderList(state,key, getCurrLoginUser().getOrgId(), offset, limit);
    }

    @GetMapping("/userOrderDtl")
    public WebResult getUserOrderDtl(String userId) {
        UserOrderDtlVo vo = orderClient.getUserOrderDtl(userId);
        return WebResult.ok(vo);
    }

    @PutMapping(value = "/userState")
    @RecordLog(desc = "修改用户状态")
    public WebResult updateUserState(String userId,Integer userState){
        userClient.updateUserState(userId,userState);
        return WebResult.ok();
    }


}