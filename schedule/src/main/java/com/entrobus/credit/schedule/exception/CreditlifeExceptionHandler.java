package com.entrobus.credit.schedule.exception;


import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.exception.CreditlifeException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常处理器
 *
 */
@RestControllerAdvice
public class CreditlifeExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(CreditlifeException.class)
	public WebResult handleCreditlifeException(CreditlifeException e){
		logger.error(e.getMsg(),e);
		return WebResult.error(e.getMsg());
	}
	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(SchedulerException.class)
	public WebResult handleCreditlifeException(SchedulerException e){
		logger.error(e.getMessage(),e);
		return WebResult.error(e.getMessage());
	}



//	@ExceptionHandler(DuplicateKeyException.class)
//	public WebResult handleDuplicateKeyException(DuplicateKeyException e){
//		logger.error(e.getMessage(), e);
//		return WebResult.error("数据库中已存在该记录");
//	}

//	@ExceptionHandler(AuthorizationException.class)
//	public WebResult handleAuthorizationException(AuthorizationException e){
//		logger.error(e.getMessage(), e);
//		return WebResult.error("没有权限，请联系管理员授权");
//	}

//	@ExceptionHandler(Exception.class)
//	public WebResult handleException(Exception e){
//		logger.error(e.getMessage(), e);
//		return WebResult.error(e.getMessage());
//	}
}
