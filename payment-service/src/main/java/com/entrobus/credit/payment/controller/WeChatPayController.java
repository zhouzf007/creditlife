package com.entrobus.credit.payment.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.payment.client.ManagerClient;
import com.entrobus.credit.payment.services.WeChatPayService;
import com.entrobus.credit.vo.loan.LoanProductVo;
import com.github.binarywang.wxpay.bean.result.WxEntPayResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ManagerClient managerClient;

    /**
     * 给指定的用户转账（测试通过）
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

    /**
     * 发送普通微信红包给指定用户（测试通过）
     * @param openId 微信用户OpenId
     * @param money 红包金额(以分为单位)
     * @return WebResult
     * @throws WxPayException
     */
    @RequestMapping("/sendRedpack")
    public WebResult sendRedpack(String openId,Integer money) throws WxPayException{
        WxPaySendRedpackResult sendRedpackResult = weChatPayService.sendRedpack(openId,money);
        return WebResult.ok(sendRedpackResult);
    }

    @RequestMapping("/getInfoByOrgId/{orgId}")
    public WebResult getInfoByOrgId(@PathVariable("orgId") String orgId) {
        LoanProductVo loanProductVo = managerClient.getInfoByOrgId(orgId);
        return WebResult.ok(loanProductVo);
    }
}
