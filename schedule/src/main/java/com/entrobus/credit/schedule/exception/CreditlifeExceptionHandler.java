package com.entrobus.credit.schedule.exception;


import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.exception.CreditlifeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	 * 参数校验失败
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public WebResult methodArgumentNotValidException(MethodArgumentNotValidException e){
		logger.error(e.getMessage(), e);

		BindingResult result = e.getBindingResult();
		if (result.hasErrors()){
			return WebResult.fail(WebResult.CODE_PARAMETERS,getValidationMsg(result));
		}
		return WebResult.fail(WebResult.CODE_PARAMETERS);
	}
	/**
     * 参数校验失败
	 * @param e
     * @return
     */
	@ExceptionHandler(BindException.class)
	public WebResult bindException(BindException e){
		logger.error(e.getMessage(), e);

		BindingResult result = e.getBindingResult();
		if (result != null && result.hasErrors()){
			return WebResult.fail(WebResult.CODE_PARAMETERS,getValidationMsg(result));
		}
		return WebResult.fail(WebResult.CODE_PARAMETERS);
	}
	private String getValidationMsg(BindingResult result) {
		StringBuilder sb = new StringBuilder();
		for (ObjectError error : result.getAllErrors()) {
			if (sb.length() > 0 ) sb.append("，");
			sb.append(error.getDefaultMessage());
		}
		sb.insert(0,"如下参数不符合规范：");
		return sb.toString();
	}

	@ExceptionHandler(Exception.class)
	public WebResult handleException(Exception e){
		logger.error(e.getMessage(), e);
		return WebResult.error("系统异常，请联系管理员");
	}

}
