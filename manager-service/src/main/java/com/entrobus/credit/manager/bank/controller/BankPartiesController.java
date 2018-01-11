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
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增资金方
     * @param parties
     * @return
     */
    @PostMapping("")
    public WebResult add(PartiesExt parties){
        return partiesService.add(parties);
    }

    /**
     * 编辑资金方
     * @param parties
     * @return
     */
    @PutMapping("/{id}")
    public WebResult edit(PartiesExt parties){
        return partiesService.edit(parties);
    }

    /**
     * 内部开发使用
     * 物理删除资金方及下属账号
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public WebResult delete(@PathVariable String id){
        return partiesService.delete(id);
    }

    /**
     * 查询资金方列表
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("")
    public WebResult list(Integer offset, Integer limit) {
        PartiesExample example = new PartiesExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
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