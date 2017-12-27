package com.entrobus.credit.common.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 给客户端的返回数据
 *
 */
public class WebResult extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public static final String CODE = "code";
	public static final String MSG = "msg";
	public static final String DATA = "data";

	public WebResult() {
		put(CODE, 0);
	}
	
	public static WebResult error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static  WebResult error(String msg) {
		return error(500, msg);
	}
	
	public static  WebResult error(int code, String msg) {
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
