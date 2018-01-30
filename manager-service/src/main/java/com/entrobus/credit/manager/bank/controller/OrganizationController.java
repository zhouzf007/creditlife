package com.entrobus.credit.manager.bank.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.annotation.RecordLog;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.manager.bank.service.OrganizationService;
import com.entrobus.credit.manager.common.bean.OrganizationExt;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.pojo.manager.Organization;
import com.entrobus.credit.pojo.manager.OrganizationExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/organization")
public class OrganizationController extends ManagerBaseController{

    @Autowired
    private OrganizationService organizationService;

    /**
     * 新增资金方
     * @param organizationExt
     * @return
     */
    @PostMapping("")
    @RecordLog(desc = "新增资金方")
    public WebResult add(OrganizationExt organizationExt){
        return organizationService.add(organizationExt);
    }

    /**
     * 编辑资金方
     * @param organizationExt
     * @return
     */
    @PutMapping("/{id}")
    @RecordLog(desc = "编辑资金方")
    public WebResult edit(OrganizationExt organizationExt){
        return organizationService.edit(organizationExt);
    }

    /**
     * 内部开发使用
     * 物理删除资金方及下属账号
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @RecordLog(desc = "物理删除资金方及下属账号")
    public WebResult delete(@PathVariable String id){
        return organizationService.delete(id);
    }

    /**
     * 查询资金方列表
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("")
    public WebResult list(Integer offset, Integer limit, String name) {
        OrganizationExample example = new OrganizationExample();
        OrganizationExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        if(ConversionUtil.isNotEmptyParameter(name)){
            criteria.andNameLike("%"+ name +"%");
        }
        example.setOrderByClause(" create_time desc ");
        PageHelper.startPage(offset,limit);
        List<Organization> organizationList = organizationService.selectByExample(example);
        PageInfo pageInfo = new PageInfo<>(organizationList);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total",pageInfo.getTotal());
        dataMap.put("rows", organizationList);
        return WebResult.ok(dataMap);
    }

}