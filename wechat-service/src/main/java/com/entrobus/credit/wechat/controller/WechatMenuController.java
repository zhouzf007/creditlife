package com.entrobus.credit.wechat.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.wechat.common.config.WxConfig;
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

    @Autowired
    private WxConfig wxConfig;

    @GetMapping("/create")
    public WebResult menuCreate() throws WxErrorException {
        //微信菜单链接
        String menuUrl = wxConfig.getServer()+"/view/index?urlFrom=wechat";
        //wxMpService.oauth2buildAuthorizationUrl生成Oauth2.0授权链接
        String oauth2Url = wxService.oauth2buildAuthorizationUrl(menuUrl, WxConsts.OAuth2Scope.SNSAPI_BASE, null);
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setName("社区贷");
        button1.setType(WxConsts.MenuButtonType.VIEW);
        button1.setUrl(oauth2Url);
        menu.getButtons().add(button1);
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
