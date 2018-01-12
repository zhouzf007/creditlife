package com.entrobus.credit.wechat.controller;

import com.entrobus.credit.wechat.common.config.WxConfig;
import com.entrobus.credit.wechat.common.controller.WeChatBaseController;
import com.entrobus.credit.wechat.common.util.WebUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Map;

@Controller
@RequestMapping("/wechat/oauth2")
public class UserOauthController extends WeChatBaseController{


    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxConfig wxConfig;

    /**
     * oauth2授权登录
     * @param request
     * @return
     */
    @GetMapping(value = "/login")
    public String oauth2Login(HttpServletRequest request) throws WxErrorException {
        logger.info("wx oauth2 redirect...");
        String code = request.getParameter("code");
        logger.info("wx oauth2 redirect, code = {}", code);
        //请求参数
        Map<String,String> parameterMap = WebUtil.getRequestParams(request);
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
        if (StringUtils.isNotBlank(code) && !"authdeny".equals(code)) {
            WxMpOAuth2AccessToken oauth2AccessToken = wxMpService.oauth2getAccessToken(code);
            boolean valid = wxMpService.oauth2validateAccessToken(oauth2AccessToken);
            if (!valid) { // token 失效，刷新token
                oauth2AccessToken = wxMpService.oauth2refreshAccessToken(oauth2AccessToken.getRefreshToken());
            }
            logger.info("oauth2AccessToken="+oauth2AccessToken);
            //WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(oauth2AccessToken, null);
            String redirectUrl;
            if(StringUtils.isNotEmpty(param)){
                redirectUrl = MessageFormat.format("{0}/credith5/#/?openId={1}&unionId={2}&{3}",wxConfig.getFrontServer(),oauth2AccessToken.getOpenId(),oauth2AccessToken.getUnionId(),param);
            }else{
                redirectUrl = MessageFormat.format("{0}/credith5/#/?openId={1}&unionId={2}",wxConfig.getFrontServer(),oauth2AccessToken.getOpenId(),oauth2AccessToken.getUnionId());
            }
            return redirect(redirectUrl);
        }
        return null;
    }

}
