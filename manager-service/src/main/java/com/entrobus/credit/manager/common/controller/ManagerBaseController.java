package com.entrobus.credit.manager.common.controller;

import com.entrobus.credit.manager.common.util.WebUtil;
import com.entrobus.credit.pojo.manager.SysUser;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Created by gacl on 2017/3/3.
 */
public class ManagerBaseController {

    //日志打印
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取当前请求对象
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            throw new RuntimeException("获取request失败", e);
        }
    }

    protected HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    protected Map<String, String> getParams(HttpServletRequest request) {
        return WebUtil.getRequestParams(request);
    }

    protected Map<String, String> getNotBlankParams(HttpServletRequest request) {
        return WebUtil.getNotBlankRequestParams(request);
    }

    /**
     * 写出数据
     *
     * @param res 输出的字符串
     * @throws Exception
     */
    protected void write(String res, HttpServletResponse response) {
        Writer writer = null;
        try {
            res = (null == res ? "" : res);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            logger.debug("输出JSON字符串：" + res);
            writer.write(res);
        } catch (IOException e) {
            logger.error("输出JSON字符串异常", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /**
     * 客户端重定向
     *
     * @param url
     * @return
     */
    protected String redirect(String url) {
        return "redirect:" + url;
    }

    /**
     * 服务端重定向
     *
     * @param url
     * @return
     */
    protected String forward(String url) {
        return "forward:" + url;
    }

    /** 基于@ExceptionHandler异常处理 */
    /*@ExceptionHandler
    @ResponseBody
    public R exp(HttpServletResponse response,Exception ex) {
        R r = new R();
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            if (ex instanceof SmartFast4jException) {
                r.setCode(((SmartFast4jException) ex).getCode());
                r.setMsg(((SmartFast4jException) ex).getMessage());
            }else if(ex instanceof DuplicateKeyException){
                r = R.fail("数据库中已存在该记录");
            }else if(ex instanceof AuthorizationException){
                r = R.fail("没有权限，请联系管理员授权");
            }else{
                r = R.fail(ex);
            }
            //记录异常日志
            logger.error(ex.getMessage(), ex);
        } catch (Exception e) {
            logger.error("ExceptionHandler 异常处理失败", e);
        }
        return r;
    }*/

    /**
     * 获取当前登录的系统用户
     * @return
     */
    protected SysUser getCurrLoginUser() {
        SysUser loginUser = (SysUser) getRequest().getSession().getAttribute("loginUser");
        return loginUser;
    }

    /**
     * 登录用户的ID
     * @return
     */
    protected Long getLoginUserId() {
        //return Constants.SUPER_ADMIN;
        return getCurrLoginUser().getId();
    }

}
