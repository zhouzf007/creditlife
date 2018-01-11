package com.entrobus.credit.wechat.interceptor;

import com.entrobus.credit.wechat.common.util.WebUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 微信请求处理拦截器
 */
public class WechatInterceptor extends HandlerInterceptorAdapter {

    protected Logger logger = LoggerFactory.getLogger(WechatInterceptor.class);
    @Autowired
    private WxMpService wxMpService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = WebUtil.getUriWidthParam(request);
        logger.info("访问url：{}", url);
        //请求参数
        Map<String, String> parameterMap = WebUtil.getRequestParams(request);
        //判断请求入口
        String urlFrom = request.getParameter("urlFrom");
        if (StringUtils.isNotEmpty(urlFrom) && urlFrom.equals("wechat")) {
            String code = request.getParameter("code");
            if (StringUtils.isNotBlank(code) && !"authdeny".equals(code)) {
                WxMpOAuth2AccessToken oauth2AccessToken = wxMpService.oauth2getAccessToken(code);
                boolean valid = wxMpService.oauth2validateAccessToken(oauth2AccessToken);
                if (!valid) { // token 失效，刷新token
                    oauth2AccessToken = wxMpService.oauth2refreshAccessToken(oauth2AccessToken.getRefreshToken());
                }
                String openId = oauth2AccessToken.getOpenId();
                String unionId = oauth2AccessToken.getUnionId();
                String redirectUrl = "http://creditlife.entrobus.com/credith5/#/?openId=" + openId + "&unionId=" + unionId + "&wxchannel=1";
                response.sendRedirect(redirectUrl);// 跳转到redirectUrl页面
                logger.info("WechatInterceptor→重定向URL，redirectUrl="+redirectUrl);
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }
}
