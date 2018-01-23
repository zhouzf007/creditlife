package com.entrobus.credit.manager.user.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.client.OrderClient;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.vo.order.OrderListVo;
import com.entrobus.credit.vo.order.UserOrderDtlVo;
import com.entrobus.credit.vo.order.UserOrderListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController extends ManagerBaseController {

    @Autowired
    private OrderClient orderClient;


    @GetMapping("/userOrderList")
    public WebResult getUserOrderList(Integer state, String orgId, Integer offset, Integer limit) {
        return orderClient.getUserOrderList(state, getCurrLoginUser().getOrgId(), offset, limit);
    }

    @GetMapping("/userOrderDtl")
    public WebResult getUserOrderDtl(String userId) {
        UserOrderDtlVo vo = orderClient.getUserOrderDtl(userId);
        return WebResult.ok(vo);
    }


    @GetMapping("/testList")
    public WebResult testList(Integer offset, Integer limit) {
        PageHelper.startPage(offset, limit);
        List<UserOrderListVo> list = new ArrayList<>();
        for (int i = (offset - 1) * limit + 1; i <= limit * offset; i++) {
            UserOrderListVo vo = new UserOrderListVo();
            vo.setId(GUIDUtil.genRandomGUID());
//            vo.setApplyTime(new Date());
//            vo.setApplyNo("NO:" + i);
            vo.setMoney(i * 100 + "");
            vo.setScore(i);
            vo.setUserName("user:" + i);
//            vo.setUpdateTime(new Date());
            vo.setMobile("12345678910");
            vo.setUserId("id:" + i);
            vo.setStateName(i % 2 == 0 ? "全部" : (i % 3 == 0 ? "待审核" : (i % 7 == 0 ? "待放款" : (i == 11 ? "已驳回" : "其他"))));
            list.add(vo);
        }
        PageInfo pageInfo = new PageInfo<>(list);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", 10 * limit);
        dataMap.put("rows", list);
        return WebResult.ok(dataMap);
    }

    @GetMapping("/testDetail")
    public WebResult testDetail(String id) {
        UserOrderDtlVo vo = new UserOrderDtlVo();
        vo.setUserId(GUIDUtil.genRandomGUID());
        vo.setIdCard("43132219891228888x");
        vo.setName("小红");
        vo.setMobile("13800138000");
        vo.setScore(99);
        vo.setAccount("建设银行信用卡0001");
        vo.setQuota("7,000,100,200,100");
        List<OrderListVo> orderList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OrderListVo listVo = new OrderListVo();
            listVo.setId(GUIDUtil.genRandomGUID());
            listVo.setApplyTime(new Date());
            listVo.setApplyNo(GUIDUtil.genRandomGUID());
            listVo.setMoney("23,555,00" + i);
//            listVo.setScore(99);
            listVo.setUserName("小红");
//            listVo.setMobile("13800138000");
//            listVo.setUserId(GUIDUtil.genRandomGUID());
//            listVo.setUpdateTime(new Date());
            listVo.setStateName("已完成");
            orderList.add(listVo);
        }
        vo.setOrderList(orderList);
        return WebResult.ok(vo);
    }

}