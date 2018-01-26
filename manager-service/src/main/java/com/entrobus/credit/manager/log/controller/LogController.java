package com.entrobus.credit.manager.log.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.bank.service.OrganizationService;
import com.entrobus.credit.manager.client.LogClient;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.sys.service.SysUserService;
import com.entrobus.credit.pojo.manager.Organization;
import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.vo.log.ManagerOperationLogDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/log")
public class LogController extends ManagerBaseController{
    @Autowired
    private LogClient logClient;
    @Autowired
    private SysUserService sysUserService;
//    @Autowired
//    private ManagerCacheService managerCacheService;
    @Autowired
    private OrganizationService organizationService;

    /**
     * 后台登陆日志
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("/loginLog")
    public WebResult sysLoginLogList(String userName ,Integer offset, Integer limit){
//        SysLoginUserInfo loginUser = getCurrLoginUser();
//        vo.setOrgId(loginUser.getOrgId());
        String sysUserId = null;
        if (StringUtils.isNotBlank(userName)) {
            SysUser operator = sysUserService.getUserByUserName(userName);
            sysUserId = operator == null ? null : String.valueOf(operator.getId());
        }

        WebResult result = logClient.sysLoginLogList(sysUserId, offset, limit);
        return result;
    }
    /**
     * 资金方操作日志列表
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("/bankOperationLog")
    public WebResult bankOperationLogList(Integer offset, Integer limit){
        SysLoginUserInfo loginUser = getCurrLoginUser();
//        vo.setOrgId(loginUser.getOrgId());
        WebResult result = logClient.bankOperationLogList(loginUser.getOrgId(), offset, limit);
        return result;
    }

    /**
     * 熵商后台操作日志列表
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("/managerOperationLog")
    public WebResult managerOperationLogList(String desc,String relId, String operatorName,Integer offset, Integer limit){
//    public WebResult managerOperationLogList(@RequestParam Map<String, Object> map, int offset, int limit){
        SysLoginUserInfo loginUser = getCurrLoginUser();
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(operatorName)){
            SysUser operator = sysUserService.getUserByUserName(operatorName);
            if (operator == null){
                return WebResult.ok().put("total",0).put("rows",new ArrayList<>(0));
            }
            map.put("operatorId",operator.getId());
        }

        map.put("desc",desc);
        map.put("relId",relId);
//        map.put("operatorName",operatorName);
        WebResult result = logClient.managerOperationLogList(map, offset, limit);
        return result;
    }

    /**
     * 操作日志详情
     * @param id
     * @return
     */
    @GetMapping("/managerOperationLog/detail")
    public WebResult managerOperationLogDetail( String id){
        if (StringUtils.isBlank(id) ) return WebResult.fail("请选择一条数据");
        SysLoginUserInfo loginUser = getCurrLoginUser();
//        vo.setOrgId(loginUser.getOrgId());
        String orgId = loginUser.getOrgId();
        ManagerOperationLogDetail detail = logClient.managerOperationLogDetail(id);
        if (StringUtils.isBlank(orgId)){
            detail.setOrgName("熵商");
        }else {
            Organization org = organizationService.selectByPrimaryKey(orgId);
            if (org != null){
                detail.setOrgName(org.getName());
            }
        }
        detail.setOperatorName(loginUser.getUsername());
        return WebResult.ok().data(detail);
    }
}
