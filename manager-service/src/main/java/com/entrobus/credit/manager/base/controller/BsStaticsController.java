package com.entrobus.credit.manager.base.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.annotation.OperationLog;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.client.BsStaticsClient;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.sys.service.LogService;
import com.entrobus.credit.vo.base.BsStaticVo;
import com.entrobus.credit.vo.log.OperationLogMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bs/statics")
public class BsStaticsController extends ManagerBaseController{
    @Autowired
    private BsStaticsClient bsStaticsClient;
    @Autowired
    private LogService logService;
    /**
     * 列表
     *
     * @param
     * @return
     */
    @GetMapping("")
    public WebResult list( String codeType, String status, Integer offset, Integer limit){
        return bsStaticsClient.list(codeType,status,offset,limit);
    }
    /**
     * 添加，并缓存
     *
     * @param vo
     * @return
     */
    @PostMapping("")
    public WebResult add( BsStaticVo vo){
        WebResult result = bsStaticsClient.add(vo);
        SysLoginUserInfo loginUser = getCurrLoginUser();
        //操作日志
        OperationLogMsg msg = new OperationLogMsg();
        msg.setDesc("新增静态数据");// 操作说明：自定义,如 提交申请（创建订单）、审核 等
        msg.setOperationData(vo);//请求参数，Object
//        msg.setOperationData(str);//请求参数，Object
        msg.setOperatorId(String.valueOf(loginUser.getId()));//操作人id,与operatorType对应管理员或用户id
//        msg.setRelId(GUIDUtil.genRandomGUID());//关联id,如orderId
        //这里跟platform对应
        msg.setOperatorType(loginUser.getPlatform());//操作人类型：0：信用贷后台管理员，1：资金方后台管理员，2-用户。
//        msg.setRemark("testLog");//备注（1024）：自定义，如：超时、定时操作等
        //操作状态：0-成功，1-失败，2-异常
        msg.setOperationState(getOperationState(result));
//        msg.setRequestId(GUIDUtil.genRandomGUID());//请求id,保留字段
        logService.operation(msg);

        return result;
    }

    private int getOperationState(WebResult result) {
        return result.isOk() ? Constants.OPERATION_STATE.SUCCESS : Constants.OPERATION_STATE.FAIL;
    }

    /**
     * 获取同类型的
     *
     * @param codeType
     * @return
     */
    @GetMapping("/similar")
    public List<BsStaticVo> getByType(@RequestParam String codeType) {
        return bsStaticsClient.getByType(codeType);
    }
    /**
     * 修改，并缓存
     * 暂时这样
     *
     * @param vo
     * @return
     */
    @PutMapping("/{id}")
    public WebResult update(@PathVariable Long id,   BsStaticVo vo){
        WebResult result = bsStaticsClient.update(id, vo);
        SysLoginUserInfo loginUser = getCurrLoginUser();
        //操作日志
        OperationLogMsg msg = new OperationLogMsg();
        msg.setDesc("编辑静态数据");// 操作说明：自定义,如 提交申请（创建订单）、审核 等
        msg.setOperationData(vo);//请求参数，Object
//        msg.setOperationData(str);//请求参数，Object
        msg.setOperatorId(String.valueOf(loginUser.getId()));//操作人id,与operatorType对应管理员或用户id
        msg.setRelId(String.valueOf(id));//关联id,如orderId
        //这里跟platform对应
        msg.setOperatorType(loginUser.getPlatform());//操作人类型：0：信用贷后台管理员，1：资金方后台管理员，2-用户。
//        msg.setRemark("testLog");//备注（1024）：自定义，如：超时、定时操作等
        //操作状态：0-成功，1-失败，2-异常
        msg.setOperationState(getOperationState(result));
//        msg.setRequestId(GUIDUtil.genRandomGUID());//请求id,保留字段
        logService.operation(msg);
        return result;
    }

    /**
     * 删除，并删除缓存
     * 暂时这样
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public WebResult del(@PathVariable Long id){
        WebResult result = bsStaticsClient.del(id);
        SysLoginUserInfo loginUser = getCurrLoginUser();
        //操作日志
        OperationLogMsg msg = new OperationLogMsg();
        msg.setDesc("删除静态数据");// 操作说明：自定义,如 提交申请（创建订单）、审核 等
        Map<String,Long> map = new HashMap<>();
        map.put("id",id);
        msg.setOperationData(map);//请求参数，Object
//        msg.setOperationData(str);//请求参数，Object
        msg.setOperatorId(String.valueOf(loginUser.getId()));//操作人id,与operatorType对应管理员或用户id
        msg.setRelId(String.valueOf(id));//关联id,如orderId
        //这里跟platform对应
        msg.setOperatorType(loginUser.getPlatform());//操作人类型：0：信用贷后台管理员，1：资金方后台管理员，2-用户。
//        msg.setRemark("testLog");//备注（1024）：自定义，如：超时、定时操作等
        //操作状态：0-成功，1-失败，2-异常
        msg.setOperationState(getOperationState(result));
//        msg.setRequestId(GUIDUtil.genRandomGUID());//请求id,保留字段
        logService.operation(msg);
        return result;
    }
    /**
     * 删除，并删除缓存
     * 暂时这样
     *
     * @param ids
     * @return
     */
    @PostMapping("/trashCan")
    public WebResult batchDel(@RequestParam("ids") List<Long> ids) {
        WebResult result = bsStaticsClient.batchDel(ids);
        SysLoginUserInfo loginUser = getCurrLoginUser();
        //操作日志
        OperationLogMsg msg = new OperationLogMsg();
        msg.setDesc("删除静态数据");// 操作说明：自定义,如 提交申请（创建订单）、审核 等
        Map<String,List<Long>> map = new HashMap<>();
        map.put("ids",ids);
        msg.setOperationData(map);//请求参数，Object
//        msg.setOperationData(str);//请求参数，Object
        msg.setOperatorId(String.valueOf(loginUser.getId()));//操作人id,与operatorType对应管理员或用户id
//        msg.setRelId(String.valueOf(id));//关联id,如orderId
        //这里跟platform对应
        msg.setOperatorType(loginUser.getPlatform());//操作人类型：0：信用贷后台管理员，1：资金方后台管理员，2-用户。
//        msg.setRemark("testLog");//备注（1024）：自定义，如：超时、定时操作等
        //操作状态：0-成功，1-失败，2-异常
        msg.setOperationState(getOperationState(result));
//        msg.setRequestId(GUIDUtil.genRandomGUID());//请求id,保留字段
        logService.operation(msg);
        return result;
    }

    /**
     * 刷新缓存，
     *
     * @param codeType 如果非空，只刷新codeType对应数据的缓存，否则刷新全部
     * @return
     */
    @PostMapping("/cache")
    public WebResult cacheOrRefreshAll( String codeType){
        return bsStaticsClient.cacheOrRefreshAll(codeType);
    }

    /**
     * 操作日志demo
     * 使用注解
     * @param str
     * @return
     */
    @PostMapping("/testLog2")
    @OperationLog
    public WebResult testLog2(String str){
        return WebResult.ok();
    }
    /**
     * 操作日志demo
     * @param str
     * @return
     */
    @PostMapping("/testLog")
    public WebResult testLog(String str){
        OperationLogMsg msg = new OperationLogMsg();
        msg.setDesc("testLog");// 操作说明：自定义,如 提交申请（创建订单）、审核 等
        Map<Object, Object> map = new HashMap<>();
        map.put("str",str);
        msg.setOperationData(map);//请求参数，Object
//        msg.setOperationData(str);//请求参数，Object
//        msg.setExtData(new HashMap<>());
        msg.setExtData("testLog");//扩展数据字段,Object
        msg.setOperatorId(GUIDUtil.genRandomGUID());//操作人id,与operatorType对应管理员或用户id
        msg.setRelId(GUIDUtil.genRandomGUID());//关联id,如orderId
        msg.setOperatorType(Constants.OPERATOR_TYPE.MANAGER);//操作人类型：0：信用贷后台管理员，1：资金方后台管理员，2-用户
        msg.setRemark("testLog");//备注（1024）：自定义，如：超时、定时操作等
        msg.setOperationState(Constants.OPERATION_STATE.SUCCESS);//操作状态：0-成功，1-失败，2-异常
        msg.setRequestId(GUIDUtil.genRandomGUID());//请求id,保留字段
        msg.setTime(new Date());//操作时间
        //
        //*****************可选扩展内容，记录相关数据操作前后的值 start
        msg.newTables("table1")//操作相关数据表1
                .putColume("abb","ddd","old")//表中字段名、新值、旧值
                .putColume("abb4","ddd1","old")
                .putColume("abb5","ddd2","old")
                .putColume("abb2","ddd24","old2");
        msg.newTables("table2")//操作相关数据表2
                .putColume("abb","ddd","old")//表中字段名、新值、旧值
                .putColume("abb4","ddd1","old")
                .putColume("abb5","ddd2","old")
                .putColume("abb2","ddd24","old2");

        //*****************可选扩展内容，记录相关数据操作前后的值 end

        //操作日志
        logService.operation(msg);

        if (Objects.equals("0",str)) return WebResult.ok("操作成功");
        if (Objects.equals("1",str)) return WebResult.error("操作失败");
        return WebResult.error("异常");
    }
}
