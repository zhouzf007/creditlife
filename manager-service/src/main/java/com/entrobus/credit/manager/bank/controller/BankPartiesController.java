package com.entrobus.credit.manager.bank.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.bank.service.PartiesService;
import com.entrobus.credit.manager.common.bean.CommonParameter;
import com.entrobus.credit.manager.common.bean.PartiesExt;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.pojo.manager.Parties;
import com.entrobus.credit.pojo.manager.PartiesExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/bank/parties")
public class BankPartiesController extends ManagerBaseController{

    @Autowired
    private PartiesService partiesService;

    @RequestMapping("/add")
    public WebResult add(PartiesExt parties){
        return partiesService.add(parties);
    }

    @RequestMapping("/edit")
    public WebResult edit(PartiesExt parties,CommonParameter commonParameter){
        return WebResult.ok("修改成功");
    }

    @RequestMapping("/delete")
    public WebResult delete(CommonParameter commonParameter){
        return WebResult.ok("删除成功");
    }

    @RequestMapping("/list")
    public WebResult list(Integer offset, Integer limit,CommonParameter commonParameter) {
        PartiesExample example = new PartiesExample();
        example.createCriteria().andStateEqualTo(0).andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        example.setOrderByClause(" create_time desc ");
        PageHelper.startPage(offset,limit);
        List<Parties> partiesList = partiesService.selectByExample(example);
        PageInfo pageInfo = new PageInfo<>(partiesList);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total",pageInfo.getTotal());
        dataMap.put("rows", partiesList);
        return WebResult.ok(dataMap);
    }

}