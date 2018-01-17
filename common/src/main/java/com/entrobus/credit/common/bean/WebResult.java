package com.entrobus.credit.common.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 给客户端的返回数据
 *
 */
public class WebResult extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public static final String CODE = "code";
	public static final String MSG = "msg";
	public static final String DATA = "data";
	public static final String TIME = "time";


	/******************** 0-399  业务逻辑相关 *********************/
	/** 操作成功 */
	public static final int  CODE_OK = 0;//0成功
	/** 操作失败 */
	public static final int  CODE_OPERATION = 6;
	/** 验证码错误 */
	public static final int  CODE_VERIFYCODE = 11;
	/** 未知的业务类型 */
	public static final int  CODE_UNKNOWN_BUSINESS = 12;
	/** 业务正在处理 */
	public static final int  CODE_BUSINESS_IS_PROCESSING = 13;
	/** 查询不到数据 */
	public static final int  CODE_SELECT_NOTFOUND = 14;
	/** 业务不允许 */
	public static final int  CODE_BUSI_DISPERMIT = 15;



	/******************** 400-499  各种拦截验证不通过，无法进入业务逻辑 *********************/
	/** 传入参数有错误 */
	public static final int  CODE_PARAMETERS = 400;//传入参数有错误
	/** 通信签名验证不通过 */
	public static final int CODE_SIGNATURE = 401;//通信签名验证不通过
	/** 版本不被支持，请检查软件版本（ver），服务端拒绝所有请求 */
	public static final int CODE_VERSION = 403;
	/** 用户未登录 */
	public static final int CODE_NOT_LOGIN = 405;
	/** 用户被冻结 */
	public static final int CODE_FROZEN = 407;
	/** token不合法或已过期 */
	public static final int  CODE_TOKEN = 406;
	/** 用户不存在 */
	public static final int  CODE_USER_NOT_FOUND = 412;
	/** 账户不可用 */
	public static final int  CODE_ACCOUNT_ABNORMAL = 413;
	/** 没有访问权限 */
	public static final int  CODE_NO_PERMISSION = 414;
	/** 接口已关闭，请联系开发人员 */
	public static final int  CODE_INTERFACE_CLOSE = 415;

	/******************** 500-599  服务器各种异常 *********************/
	/** 服务异常  */
	public static final int  CODE_ERROR = 500;//异常
	/** 未知错误  */
	public static final int  CODE_ERROR_UNKNOWN = 506;//异常

	/******************** 700-799  网关拦截相关 *********************/
	/** 您的操作过于频繁，请稍后再试 */
	public static final int  CODE_TOO_OFTENL = 700;
	/** 请勿重复操作 */
	public static final int  CODE_REPEAT_OPERATION = 701;



	public static final Map<Integer,String> CODE_MSG = new HashMap<>();
	static {
		CODE_MSG.put(CODE_OK,"操作成功");
		CODE_MSG.put(CODE_OPERATION,"操作失败");
		CODE_MSG.put(CODE_VERIFYCODE,"验证码错误");
		CODE_MSG.put(CODE_UNKNOWN_BUSINESS,"未知的业务类型");
		CODE_MSG.put(CODE_BUSINESS_IS_PROCESSING,"业务正在处理");
		CODE_MSG.put(CODE_SELECT_NOTFOUND,"查询不到数据");
		CODE_MSG.put(CODE_BUSI_DISPERMIT,"业务不允许");

		CODE_MSG.put(CODE_PARAMETERS,"传入参数有错误");
		CODE_MSG.put(CODE_SIGNATURE,"通信签名验证不通过");
		CODE_MSG.put(CODE_VERSION,"版本不被支持，请检查软件版本");
		CODE_MSG.put(CODE_NOT_LOGIN,"用户未登录");
		CODE_MSG.put(CODE_FROZEN,"用户被冻结");
		CODE_MSG.put(CODE_TOKEN,"token不合法或已过期");
		CODE_MSG.put(CODE_USER_NOT_FOUND,"用户不存在");
		CODE_MSG.put(CODE_ACCOUNT_ABNORMAL,"账户不可用");
		CODE_MSG.put(CODE_NO_PERMISSION,"没有访问权限");
		CODE_MSG.put(CODE_INTERFACE_CLOSE,"接口已关闭，请联系开发人员");

		CODE_MSG.put(CODE_ERROR,"服务异常");
		CODE_MSG.put(CODE_ERROR_UNKNOWN,"未知错误");

		CODE_MSG.put(CODE_TOO_OFTENL,"您的操作过于频繁，请稍后再试 ");
		CODE_MSG.put(CODE_REPEAT_OPERATION,"请勿重复操作 ");
	}

	public WebResult() {
		put(CODE, CODE_OK);
		put(TIME,System.currentTimeMillis());
	}
	public boolean isOk(){
		Object code = get(CODE);
		return  Objects.equals(code ,CODE_OK);
	}
	public static WebResult error() {
		return error(CODE_ERROR, CODE_MSG.get(CODE_ERROR));
	}
	
	public static  WebResult error(String msg) {
		return error(CODE_ERROR, msg);
	}
	
	public static  WebResult error(int code, String msg) {
		return result(code, msg);
	}
	public static  WebResult fail(int code, String msg) {
		return result(code, msg);
	}
	public static  WebResult fail(int code) {
		return fail(code, CODE_MSG.getOrDefault(code,""));
	}
	public static  WebResult fail(String msg) {
		return fail(CODE_OPERATION, msg);
	}
	public static  WebResult fail() {
		return fail(CODE_OPERATION, CODE_MSG.get(CODE_OPERATION));
	}

	private static WebResult result(int code, String msg) {
		WebResult webResult = new WebResult();
		webResult.put(CODE, code);
		webResult.put(MSG, msg);
		return webResult;
	}

	public static  WebResult ok(String msg) {
		WebResult webResult = new WebResult();
		webResult.put(MSG, msg);
		return webResult;
	}

	public static  WebResult ok(Object data) {
		WebResult webResult = new WebResult();
		webResult.put(DATA, data);
		return webResult;
	}
	
	public static  WebResult ok(Map<String, Object> map) {
		WebResult webResult = new WebResult();
		webResult.putAll(map);
		return webResult;
	}
	
	public static  WebResult ok() {
		return new WebResult();
	}

	public WebResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
