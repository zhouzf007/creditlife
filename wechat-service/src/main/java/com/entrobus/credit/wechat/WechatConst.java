
package com.entrobus.credit.wechat;

/**
 * 微信公众号常量
 *
 */
public interface WechatConst {

    /**
     * 公众号相关常量
     */
    interface PublicAccount {
       Integer SUBSCRIBE = 0; // 0：已关注
       Integer UNSUBSCRIBE = 1; // 1：取消关注
       Integer NOT_SUBSCRIBE = 2; // 2：未关注
    }
}
