package com.entrobus.credit.wechat.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.wechat.common.controller.WeChatBaseController;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信菜单Controller
 */
@RestController
@RequestMapping("/wechat/menu")
public class WechatMenuController extends WeChatBaseController {

    @Autowired
    private WxMpService wxService;

    @GetMapping("/create")
    public WebResult menuCreate() throws WxErrorException {

        String menuUrl1 = "http://creditlife.entrobus.com/credith5/#/login"+"?urlFrom=wechat";
        String menuUrl2 = "http://creditlife.entrobus.com/credith5/#/zhuce"+"?urlFrom=wechat";
        String oauth2Url1 = wxService.oauth2buildAuthorizationUrl(menuUrl1, WxConsts.OAuth2Scope.SNSAPI_BASE, null);
        String oauth2Url2 = wxService.oauth2buildAuthorizationUrl(menuUrl2, WxConsts.OAuth2Scope.SNSAPI_BASE, null);
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setName("社区贷-登录");
        button1.setType(WxConsts.MenuButtonType.VIEW);
        button1.setUrl(oauth2Url1);
//        WxMenuButton button2 = new WxMenuButton();
//        button2.setType(WxConsts.BUTTON_MINIPROGRAM);
//        button2.setName("小程序");
//        button2.setAppId("wx286b93c14bbf93aa");
//        button2.setPagePath("pages/lunar/index.html");
//        button2.setUrl("http://mp.weixin.qq.com");

        WxMenuButton button3 = new WxMenuButton();
        button3.setName("社区贷-注册");
        button3.setType(WxConsts.MenuButtonType.VIEW);
        button3.setUrl(oauth2Url2);

        menu.getButtons().add(button1);
//        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        return WebResult.ok(this.wxService.getMenuService().menuCreate(menu));
    }

    /**
     * 自定义菜单删除接口
     */
    @GetMapping("/delete")
    public WebResult menuDelete() throws WxErrorException {
        this.wxService.getMenuService().menuDelete();
        return WebResult.ok();
    }
}
