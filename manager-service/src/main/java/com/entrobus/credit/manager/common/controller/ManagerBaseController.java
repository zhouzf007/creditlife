package com.entrobus.credit.manager.common.controller;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.util.WebUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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


    @Autowired
    protected RedisTemplate redisTemplate;

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

    /**
     * 获取当前登录的系统用户
     * @return
     */
    protected SysLoginUserInfo getCurrLoginUser() {
        String token = getRequest().getParameter("token");
        SysLoginUserInfo loginUser = CacheService.getCacheObj(redisTemplate,token,SysLoginUserInfo.class);
        return loginUser;
    }

    /**
     * 登录用户的ID
     * @return
     */
    protected Long getLoginUserId() {
        return getCurrLoginUser().getId();
    }

}
