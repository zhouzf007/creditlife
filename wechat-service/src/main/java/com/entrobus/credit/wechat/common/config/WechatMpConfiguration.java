package com.entrobus.credit.wechat.common.config;

import com.entrobus.credit.wechat.handler.*;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static me.chanjar.weixin.common.api.WxConsts.*;

/**
 * wechat mp configuration
 */
@Configuration
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties(WxConfig.class)
public class WechatMpConfiguration {

  @Autowired
  private WxConfig wxConfig;

  @Autowired
  private LogHandler logHandler;

  @Autowired
  private NullHandler nullHandler;

  @Autowired
  private MenuHandler menuHandler;

  @Autowired
  private MsgHandler msgHandler;

  @Autowired
  private UnsubscribeHandler unsubscribeHandler;

  @Autowired
  private SubscribeHandler subscribeHandler;

  @Bean
  @ConditionalOnMissingBean
  public WxMpConfigStorage configStorage() {
    WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
    configStorage.setAppId(this.wxConfig.getAppId());
    configStorage.setSecret(this.wxConfig.getAppSecret());
    configStorage.setToken(this.wxConfig.getToken());
    configStorage.setAesKey(this.wxConfig.getAesKey());
    return configStorage;
  }

  @Bean
  @ConditionalOnMissingBean
  public WxMpService wxMpService(WxMpConfigStorage configStorage) {
    WxMpService wxMpService = new WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(configStorage);
    return wxMpService;
  }

  @Bean
  public WxMpMessageRouter router(WxMpService wxMpService) {
    final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

    // 记录所有事件的日志 （异步执行）
    newRouter.rule().handler(this.logHandler).next();
    // 自定义菜单事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(MenuButtonType.CLICK).handler(this.menuHandler).end();
    // 点击菜单连接事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(MenuButtonType.VIEW).handler(this.nullHandler).end();
    // 关注事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.SUBSCRIBE).handler(this.subscribeHandler)
        .end();
    // 取消关注事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.UNSUBSCRIBE)
        .handler(this.unsubscribeHandler).end();
    // 默认
    newRouter.rule().async(false).handler(this.msgHandler).end();
    return newRouter;
  }
}
