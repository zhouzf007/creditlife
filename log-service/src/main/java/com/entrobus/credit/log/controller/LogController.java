package com.entrobus.credit.log.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ServletUtil;
import com.entrobus.credit.log.service.ClientLogService;
import com.entrobus.credit.log.service.LogCacheService;
import com.entrobus.credit.log.service.OperationLogService;
import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.pojo.log.ClientLog;
import com.entrobus.credit.pojo.log.OperationLog;
import com.entrobus.credit.vo.common.CommonParameter;
import com.entrobus.credit.vo.log.BankOperationLogVo;
import com.entrobus.credit.vo.log.LogQueryVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LogController {
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private ClientLogService clientLogService;
    @Autowired
    private LogCacheService logCacheService;

    @PostMapping(value = "/garbage")
    public int clear(){
        return operationLogService.clear();
    }

    @PostMapping(value = "/client")
    public void clientLog(@RequestParam String form, CommonParameter parameter, HttpServletRequest request){
        if (StringUtils.isBlank(form)) return;
        ClientLog log = new ClientLog();
        log.setAddress(ServletUtil.getIpAddr(request));
        log.setContent(form);
        if (StringUtils.isNotBlank(parameter.getClient()))
            log.setClient(Integer.valueOf(parameter.getClient()));
        clientLogService.insertSelective(log);
    }
    @GetMapping("/bank/operationLog")
    public WebResult bankOperationLogList( LogQueryVo queryVo,
                                                         @RequestParam(defaultValue = "1")int pageNum,
                                                         @RequestParam(defaultValue = "20") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OperationLog> list = operationLogService.getByQueryVo(queryVo);
        List<BankOperationLogVo> voList = list.stream().map(this::toBankOperationLogVo).collect(Collectors.toList());
        PageInfo<OperationLog> pageInfo = new PageInfo<>(list);
        return WebResult.ok("操作成功").put("total",pageInfo.getTotal()).put("rows",voList);
    }

    private BankOperationLogVo toBankOperationLogVo(OperationLog log) {
        BankOperationLogVo vo = new BankOperationLogVo();
        vo.setId(log.getId());
        vo.setDesc(log.getOperationDesc());
        vo.setOperationState(log.getOperationState());
        vo.setOperatorId(log.getOperatorId());
        vo.setRemark(log.getRemark());
        vo.setTime(log.getOperationTime());
        String stateName = logCacheService.translation(Cachekey.Translation.LOG_OPERATION_STATE, log.getOperationState());
        vo.setOperationStateName(stateName);
        if (StringUtils.isNotBlank(log.getResult())) {
            try {
                vo.setResultMsg(JSON.parseObject(log.getResult()).getString("msg"));
            } catch (RuntimeException e) {
                vo.setResultMsg(log.getResult());
            }
        }
        return vo;
    }
}
