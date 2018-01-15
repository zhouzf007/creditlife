package com.entrobus.credit.manager.common.aop;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.sys.service.LogService;
import com.entrobus.credit.vo.log.OperationLogMsg;
import com.entrobus.credit.common.annotation.OperationLog ;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现AOP的切面主要有以下几个要素：

 使用@Aspect注解将一个java类定义为切面类
 使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
 根据需要在切入点不同位置的切入内容
 使用@Before在切入点开始处切入内容
 使用@After在切入点结尾处切入内容
 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 使用@Order(i)注解来标识切面的优先级。i的值越小，优先级越高
 */
@Aspect
@Order(Integer.MAX_VALUE)
@Component
public class OperationLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);
    @Autowired
    private LogService logService;

//    @Pointcut("execution(public * com.entrobus.credit.manager.*.controller..*.*(..))")
    @Pointcut( "@annotation(com.entrobus.credit.common.annotation.OperationLog)")
    public void operationLog(){}

    @Around(value = "operationLog()")
    public void doAround(ProceedingJoinPoint pjp) throws Throwable {

        OperationLogMsg msg = getOperationLogMsg(pjp);
        //执行业务
        Object proceed = pjp.proceed();
        try {
            //操作状态
            msg.setOperationState(getOperationState(proceed));
            //记录日志
            logService.operation(msg);
        } catch (Exception e) {
            logger.error(String.format("通过注解记录操作日志发布失败！操作参数：%s", JSON.toJSONString(pjp.getArgs())),e);
        }


    }

    private OperationLogMsg getOperationLogMsg(ProceedingJoinPoint pjp) {
        OperationLogMsg msg = null;
        try {
            Object[] args = pjp.getArgs();
            Signature sig = pjp.getSignature();
            MethodSignature msig = null;
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            msig = (MethodSignature) sig;
            Object target = pjp.getTarget();
            Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            OperationLog logAnnotation = currentMethod.getAnnotation(OperationLog.class);
            //todo 登录用户信息
            //操作日志
            msg = new OperationLogMsg();
            msg.setDesc(logAnnotation.desc());// 操作说明：自定义,如 提交申请（创建订单）、审核 等
            msg.setOperationData(args);//请求参数，Object
//        msg.setOperationData(str);//请求参数，Object
//        msg.setOperatorId(String.valueOf(loginUser.getId()));//操作人id,与operatorType对应管理员或用户id
//        msg.setRelId(String.valueOf(id));//关联id,如orderId
            //这里跟platform对应
//        msg.setOperatorType(loginUser.getPlatform());//操作人类型：0：信用贷后台管理员，1：资金方后台管理员，2-用户。
            msg.setRemark(logAnnotation.remark());//备注（1024）：自定义，如：超时、定时操作等
            //操作状态：0-成功，1-失败，2-异常

//        msg.setRequestId(GUIDUtil.genRandomGUID());//请求id,保留字段
        } catch (Exception e) {
            logger.error("执行操作前获取操作信息失败",e);
        }
        return msg;
    }

    //    @AfterReturning(returning = "ret", pointcut = "operationLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        logger.info("处理完请求，返回内容 : " + ret);
//    }
    protected int getOperationState(Object object) {
        if (object instanceof  WebResult) {
            WebResult result = (WebResult)object;
            return result.isOk() ? Constants.OPERATION_STATE.SUCCESS : Constants.OPERATION_STATE.FAIL;
        }
        return Constants.OPERATION_STATE.SUCCESS;
    }

}
