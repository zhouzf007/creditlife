package com.entrobus.credit.payment.exception;


import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.exception.CreditlifeException;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
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

	@ExceptionHandler(DuplicateKeyException.class)
	public WebResult handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return WebResult.error("数据库中已存在该记录");
	}

	/**
	 * 处理微信异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(WxErrorException.class)
	public WebResult handleWxErrorException(WxErrorException e){
		logger.error(e.getError().getErrorMsg(), e);
		return WebResult.error("微信请求出错，请联系管理员");
	}

	/**
	 * 处理系统异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public WebResult handleException(Exception e){
		logger.error(e.getMessage(), e);
		return WebResult.error("系统出错，请联系管理员");
	}
}
