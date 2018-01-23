package com.entrobus.credit.log.controller;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.common.util.ServletUtil;
import com.entrobus.credit.log.client.ManagerClient;
import com.entrobus.credit.log.service.ClientLogService;
import com.entrobus.credit.log.service.LogCacheService;
import com.entrobus.credit.log.service.OperationLogService;
import com.entrobus.credit.pojo.log.ClientLog;
import com.entrobus.credit.pojo.log.OperationLog;
import com.entrobus.credit.vo.common.CommonParameter;
import com.entrobus.credit.vo.log.BankOperationLogVo;
import com.entrobus.credit.vo.log.LogQueryVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LogController {
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private ClientLogService clientLogService;
    @Autowired
    private LogCacheService logCacheService;
    @Autowired
    private ManagerClient managerClient;

    @PostMapping(value = "/garbage")
    public int clear(){
        return operationLogService.clear();
    }

    @PostMapping(value = "/client")
    public WebResult clientLog(@RequestParam String form, CommonParameter parameter, HttpServletRequest request){
        if (StringUtils.isBlank(form)) return WebResult.fail(WebResult.CODE_PARAMETERS);
        ClientLog log = new ClientLog();
        log.setAddress(ServletUtil.getIpAddr(request));
        log.setContent(form);
        if (StringUtils.isNotBlank(parameter.getClient()))
            log.setClient(Integer.valueOf(parameter.getClient()));
        clientLogService.insertSelective(log);
        return WebResult.ok();
    }
    @GetMapping("/bank/operationLog")
    public WebResult bankOperationLogList( LogQueryVo queryVo,
                                                         @RequestParam(defaultValue = "1")int pageNum,
                                                         @RequestParam(defaultValue = "20") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OperationLog> list = operationLogService.getByQueryVo(queryVo);
        Map<String, String> longStringMap = managerClient.userNameMapByOrg(queryVo.getOrgId());
//        List<BankOperationLogVo> voList = list.stream().map(this::toBankOperationLogVo).collect(Collectors.toList());
        List<BankOperationLogVo> voList = new ArrayList<>();
        for (OperationLog log : list) {
            BankOperationLogVo logVo = toBankOperationLogVo(log);
            String operatorName = longStringMap.getOrDefault(log.getOperatorId(), "");
            logVo.setOperatorName(operatorName);
            voList.add(logVo);
        }
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
        vo.setTime(DateUtils.formatDateTime(log.getOperationTime()));
        String stateName = logCacheService.translate(Constants.CODE_TYPE.OPERATION_STATE, log.getOperationState());
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
