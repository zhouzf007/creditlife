package com.entrobus.credit.payment.services;

import com.github.binarywang.wxpay.bean.result.WxEntPayResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
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

    /**
     * 发送普通微信红包给指定用户
     * @param openId 微信用户OpenId
     * @param money 红包金额
     * @return WxPaySendRedpackResult
     * @throws WxPayException
     */
    WxPaySendRedpackResult sendRedpack(String openId,Integer money) throws WxPayException;
}
