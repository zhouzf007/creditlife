package com.entrobus.credit.payment.services;

import com.github.binarywang.wxpay.bean.result.WxEntPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 微信支付Service
 */
public interface WeChatPayService {

    /**
     * 给指定的用户转账
     * @param openId 需要打款的微信用户的openId
     * @param money 打款的金额
     * @return WxEntPayResult
     * @throws WxPayException
     */
    WxEntPayResult entPay(String openId,Integer money) throws WxPayException;
}
