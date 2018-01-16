package com.entrobus.credit.manager.order.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.client.OrderClient;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.vo.order.OrderListVo;
import com.entrobus.credit.vo.order.OrderQueryVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController extends ManagerBaseController {

    @Autowired
    private OrderClient OrderClient;

    /**
     * 查询资金方列表
     *
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("")
    public WebResult list(Integer offset, Integer limit,String key) {
        PageHelper.startPage(offset, limit);
        OrderQueryVo qvo = new OrderQueryVo();
        qvo.setLimit(limit);
        qvo.setOffset(offset);
        qvo.setKey(key);
        List<OrderListVo> list = OrderClient.getOrderList(qvo);
        PageInfo pageInfo = new PageInfo<>(list);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", pageInfo.getTotal());
        dataMap.put("rows", list);
        return WebResult.ok(dataMap);
    }

}