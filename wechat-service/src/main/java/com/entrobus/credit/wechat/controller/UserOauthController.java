package com.entrobus.credit.wechat.controller;

import com.entrobus.credit.wechat.common.controller.WeChatBaseController;
import com.entrobus.credit.wechat.common.util.WebUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/wechat/oauth2")
public class UserOauthController extends WeChatBaseController{


    @Autowired
    private WxMpService wxMpService;

    /**
     * oauth2授权登录
     * @param request
     * @return
     */
    @GetMapping(value = "/login")
    public WxMpUser oauth2Login(HttpServletRequest request) throws WxErrorException {
        logger.info("wx oauth2 redirect...");
        String code = request.getParameter("code");
        logger.info("wx oauth2 redirect, code = {}", code);

        //请求参数
        Map<String,String> parameterMap = WebUtil.getRequestParams(request);
        if (StringUtils.isNotBlank(code) && !"authdeny".equals(code)) {
            WxMpOAuth2AccessToken oauth2AccessToken = wxMpService.oauth2getAccessToken(code);
            boolean valid = wxMpService.oauth2validateAccessToken(oauth2AccessToken);
            if (!valid) { // token 失效，刷新token
                oauth2AccessToken = wxMpService.oauth2refreshAccessToken(oauth2AccessToken.getRefreshToken());
            }
            //logger.info("oauth2AccessToken="+oauth2AccessToken);
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(oauth2AccessToken, null);
            return wxMpUser;
        }
        return null;
    }

}
