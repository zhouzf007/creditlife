package com.entrobus.credit.manager.base.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.common.client.BsStaticsClient;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.sys.service.LogService;
import com.entrobus.credit.vo.base.BsStaticVo;
import com.entrobus.credit.vo.log.OperationLogMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        return bsStaticsClient.add(vo);
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
        return bsStaticsClient.update(id,vo);
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
        return bsStaticsClient.del(id);
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
        return bsStaticsClient.batchDel(ids);
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
    @PostMapping("/testLog")
    public WebResult testLog(String str){
        OperationLogMsg msg = new OperationLogMsg();
        msg.setDesc("testLog");// 操作说明：自定义,如 提交申请（创建订单）、审核 等
//        msg.setOperationData(new HashMap<>());
        msg.setOperationData("testLog");//请求参数
//        msg.setExtData(new HashMap<>());
        msg.setExtData("testLog");//扩展字段
        msg.setOperatorId(GUIDUtil.genRandomGUID());//操作人id,与operatorType对应管理员或用户id
        msg.setRelId(GUIDUtil.genRandomGUID());//关联id,如orderId
        msg.setOperatorType(Constants.OPERATOR_TYPE.MANAGER);//操作人类型：0：信用贷后台管理员，1：资金方后台管理员，2-用户
        msg.setRemark("testLog");//备注（1024）：自定义，如：超时、定时操作等
        msg.setOperationState(Constants.OPERATION_STATE.SUCCESS);//操作状态：0-成功，1-失败，2-异常
        msg.setRequestId(GUIDUtil.genRandomGUID());
        msg.setTime(new Date());//操作时间
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

        logService.operation(msg);

        if (Objects.equals("0",str)) return WebResult.ok("操作成功");
        if (Objects.equals("1",str)) return WebResult.error("操作失败");
        return WebResult.error("异常");
    }
}
