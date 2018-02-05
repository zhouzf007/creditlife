package com.entrobus.credit.order.aop;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.annotation.RecordLog;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.order.services.LogService;
import com.entrobus.credit.order.services.OrderCacheService;
import com.entrobus.credit.vo.common.CommonParameter;
import com.entrobus.credit.vo.log.OperationLogMsg;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
 参考http://blog.csdn.net/YLIMH_HMILY/article/details/78303464
 */
@Aspect
@Order(Integer.MAX_VALUE)
@Component
public class OperationLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);
    @Autowired
    private LogService logService;
    @Autowired
    private OrderCacheService cacheService;

//    @Pointcut("execution(public * com.entrobus.credit.manager.*.controller..*.*(..))")
    @Pointcut( "@annotation(com.entrobus.credit.common.annotation.RecordLog)")
    public void recordLog(){}

    @Around(value = "recordLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        OperationLogMsg msg = createOperationLogMsg(pjp);
        //执行业务
        Object proceed = pjp.proceed();
        try {
            //操作状态
            msg.setOperationState(getOperationState(proceed));
            msg.setResult(proceed);
            //记录日志
            logService.operation(msg);
        } catch (Exception e) {
            logger.error(String.format("通过注解记录操作日志发布失败！操作参数：%s", JSON.toJSONString(pjp.getArgs())),e);
        }

        return proceed;//返回值
    }

    /**
     * 根据切面信息
     * @param pjp
     * @return
     */
    protected OperationLogMsg createOperationLogMsg(ProceedingJoinPoint pjp) {
        OperationLogMsg msg = null;
        try {

            Method currentMethod = getCurrentMethod(pjp);
            RecordLog logAnnotation = currentMethod.getAnnotation(RecordLog.class);

            // 登录用户信息
            //操作日志
            msg = new OperationLogMsg();
            msg.setDesc(logAnnotation.desc());// 操作说明：自定义,如 提交申请（创建订单）、审核 等
            //设置操作人信息
            operatorMsg(msg);
            //获取相关方法参数
            Object[] args = pjp.getArgs();
            String[] argNames = getArgNames(currentMethod);

            if (args != null && argNames != null && args.length>0){
                Map<String,Object> argMap = new HashMap<>(args.length);
                for (int i = 0; i < args.length; i++) {
                    Object arg = args[i];
                    if (arg instanceof BindingResult){
                        //这是用于参数校验结果的，@Validated 或 @Valid
                        msg.setExtData(arg);//扩展字段
                        continue;
                    }
                    if (isIgnoreArg(arg)) continue;
                    String name = argNames[i];
                    argMap.put(name, arg);
                    if (Objects.equals(logAnnotation.relId(), name)) {
                        msg.setRelId(arg == null ? null : arg.toString());//关联id,如orderId
                    }
                }
                msg.setOperationData(argMap);//请求参数，Object
            }

            //这里跟platform对应
            msg.setRemark(logAnnotation.remark());//备注（1024）：自定义，如：超时、定时操作等
            //操作状态：0-成功，1-失败，2-异常
//        msg.setRequestId(GUIDUtil.genRandomGUID());//请求id,保留字段
        } catch (Exception e) {
            logger.error("执行操作前获取操作信息失败",e);
        }
        return msg;
    }

    /**
     * 判断是否忽略该参数，如果不需要记录到日志里，则返回true
     * @param arg
     * @return
     */
    protected boolean isIgnoreArg(Object arg) {
        return arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof CommonParameter;
    }

    /**
     * 操作人信息
     * @param msg
     */
    protected void operatorMsg(OperationLogMsg msg) {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(getToken());
        if (userInfo != null) {
            msg.setOperatorId(userInfo.getId());//操作人id,与operatorType对应管理员或用户id
            msg.setPlatform(Constants.PLATFORM.USER);//操作人类型：0：信用贷后台管理员，1：资金方后台管理员，2-用户。
//            msg.setOrgId(userInfo.getOrgId());
        }
    }
    /**
     * 获取当前请求对象
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            throw new RuntimeException("获取request失败", e);
        }
    }

    private String getToken(){
        HttpServletRequest request = getRequest();
        String token = request.getParameter("token");
        if (token == null) token = request.getHeader("token");
        return token;
    }
    /**
     * 获取当前方法
     * @param pjp
     * @return
     * @throws NoSuchMethodException
     */
    protected Method getCurrentMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = pjp.getTarget();
        return target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
    }

    /**
     * 获取参数名
     * 为了避免拿不到实际参数名的情况（JDK版本小于1.8）
     * 优先使用@PathVariable和@RequestParam绑定值，如果没有，则使用Parameter.getName()
     *
     * @param currentMethod
     * @return
     */
    protected String[]  getArgNames(Method currentMethod ) {
        Parameter[] parameters = currentMethod.getParameters();
        if (parameters != null && parameters.length > 0){
            String[]  argNames = new String[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                Parameter param = parameters[i];
                if (param.isAnnotationPresent(PathVariable.class)){
                    PathVariable pv = param.getAnnotation(PathVariable.class);
                    String name = pv.value();
                    argNames[i]  = StringUtils.isBlank(name) ? pv.name() : name;
                }else if (param.isAnnotationPresent(RequestParam.class)){
                    RequestParam pv = param.getAnnotation(RequestParam.class);
                    String name = pv.value();
                    argNames[i] = StringUtils.isBlank(name) ? pv.name() : name;
                }
                if (StringUtils.isBlank(argNames[i])) {
                    argNames[i] = param.getName();
                }
            }
            return argNames;
        }
        return null;
    }

    /**
     * 判断操作状态
     * @param object
     * @return 0-成功，1-失败，2异常
     */
    protected int getOperationState(Object object) {
        if (object != null && object instanceof  WebResult) {
            WebResult result = (WebResult)object;
            if (result.isOk()) return Constants.OPERATION_STATE.SUCCESS;
            return result.isError() ? Constants.OPERATION_STATE.ERROR : Constants.OPERATION_STATE.FAIL;
        }
        return Constants.OPERATION_STATE.SUCCESS;
    }
}