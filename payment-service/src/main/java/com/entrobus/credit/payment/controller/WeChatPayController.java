package com.entrobus.credit.payment.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.payment.services.WeChatPayService;
import com.github.binarywang.wxpay.bean.result.WxEntPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信支付Controller
 */
@RestController
@RequestMapping("/weChatPay")
public class WeChatPayController extends PaymentBaseController{

    @Autowired
    private WeChatPayService weChatPayService;

    /**
     * 给指定的用户转账
     * @param openId 需要打款的微信用户的openId
     * @param money 打款的金额(以分为单位)
     * @return WebResult
     * @throws WxPayException
     */
    @RequestMapping("/entPay")
    public WebResult entPay(String openId,Integer money) throws WxPayException{
        WxEntPayResult payResult = weChatPayService.entPay(openId,money);
        return WebResult.ok(payResult);
    }
}
