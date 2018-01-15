package com.entrobus.credit.wechat.interceptor;

import com.entrobus.credit.wechat.common.config.WxConfig;
import com.entrobus.credit.wechat.common.util.WebUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.Map;

/**
 * 微信请求处理拦截器
 */
@Component
public class WechatInterceptor extends HandlerInterceptorAdapter {

    protected Logger logger = LoggerFactory.getLogger(WechatInterceptor.class);

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxConfig wxConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = WebUtil.getUriWidthParam(request);
        logger.debug("访问url：{}", url);
        //请求参数
        Map<String, String> parameterMap = WebUtil.getRequestParams(request);
        String param = "";
        if(parameterMap!=null && parameterMap.size()>0){
            //带参数访问
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                String paramName = entry.getKey();
                String paramValue = entry.getValue();
                if(StringUtils.isNotEmpty(paramValue)){
                    param += paramName + "=" + paramValue + "&";
                }
            }
            if (StringUtils.isNotEmpty(param)) {
                param = param.substring(0, param.length() - 1);
            }
        }
        //判断请求入口
        String urlFrom = request.getParameter("urlFrom");
        if (StringUtils.isNotEmpty(urlFrom) && urlFrom.equals("wechat")) {
            //用户是通过点击微信公众号菜单进入的
            String code = request.getParameter("code");
            if (StringUtils.isNotBlank(code) && !"authdeny".equals(code)) {
                WxMpOAuth2AccessToken oauth2AccessToken = wxMpService.oauth2getAccessToken(code);
                boolean valid = wxMpService.oauth2validateAccessToken(oauth2AccessToken);
                if (!valid) { // token 失效，刷新token
                    oauth2AccessToken = wxMpService.oauth2refreshAccessToken(oauth2AccessToken.getRefreshToken());
                }
                String redirectUrl;
                if(StringUtils.isNotEmpty(param)){
                    redirectUrl = MessageFormat.format("{0}/credith5/#/?openId={1}&unionId={2}&{3}",wxConfig.getFrontServer(),oauth2AccessToken.getOpenId(),oauth2AccessToken.getUnionId(),param);
                }else{
                    redirectUrl = MessageFormat.format("{0}/credith5/#/?openId={1}&unionId={2}",wxConfig.getFrontServer(),oauth2AccessToken.getOpenId(),oauth2AccessToken.getUnionId());
                }
                response.sendRedirect(redirectUrl);// 跳转到redirectUrl页面
                logger.debug("WechatInterceptor→重定向URL，redirectUrl="+redirectUrl);
                return false;
            }
        }else{
            //用户是通过普通连接访问的
            //wxMpService.oauth2buildAuthorizationUrl生成Oauth2.0授权链接，然后跳转到/wechat/oauth2/login进行授权登录
            String oauth2Url = wxMpService.oauth2buildAuthorizationUrl(wxConfig.getServer()+"/wechat/oauth2/login?"+param, WxConsts.OAuth2Scope.SNSAPI_BASE, null);
            logger.debug("跳转到微信授权登录链接进行微信登录oauth2Url:" + oauth2Url);
            response.sendRedirect(oauth2Url);
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
