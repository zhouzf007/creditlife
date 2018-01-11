package com.entrobus.credit.wechat.handler;

import com.entrobus.credit.wechat.service.WechatUserService;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户取消关注公众号时的处理handler
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

  @Autowired
  private WechatUserService wechatUserService;

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) {
    String openId = wxMessage.getFromUser();
    this.logger.info("取消关注用户 OPENID: " + openId);
    //更新为取消关注状态
    wechatUserService.unSubscribe(openId);
    return null;
  }

}
