package com.entrobus.credit.payment.services.impl;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.common.util.IpUtil;
import com.entrobus.credit.payment.config.WxPayProperties;
import com.entrobus.credit.payment.services.WeChatPayService;
import com.github.binarywang.wxpay.bean.request.WxEntPayRequest;
import com.github.binarywang.wxpay.bean.result.WxEntPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxPayService wxService;

    @Autowired
    private WxPayProperties properties;


    /**
     * 给指定的用户转账
     * @param openId 需要打款的微信用户的openId
     * @param money 打款的金额
     * @return WxEntPayResult
     * @throws WxPayException
     */
    @Override
    public WxEntPayResult entPay(String openId, Integer money) throws WxPayException {
        WxEntPayRequest entPayRequest = new WxEntPayRequest();
        entPayRequest.setAppid(properties.getAppId());//公众账号appid
        entPayRequest.setMchId(properties.getMchId());//商户号
        entPayRequest.setNonceStr(String.valueOf(System.currentTimeMillis()));//随机字符串
        entPayRequest.setPartnerTradeNo(GUIDUtil.genRandomGUID());//订单号，不能超过32位
        entPayRequest.setAmount(money);//商品支付金额,总金额以分为单位，不带小数点
        entPayRequest.setOpenid(openId);//微信用户的openId
        // 微信客户端生成订单的IP地址
        String ip = IpUtil.getLocalIP();
        if (StringUtils.isBlank(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        entPayRequest.setSpbillCreateIp(ip);//调用接口的机器Ip地址
        entPayRequest.setCheckName("NO_CHECK");//校验用户姓名选项 NO_CHECK：不校验真实姓名,FORCE_CHECK：强校验真实姓名
        entPayRequest.setDescription("放款");
        //进行企业转账并返回转账结果
        WxEntPayResult payResult = wxService.entPay(entPayRequest);
        logger.info("企业转账结果：" + JSON.toJSONString(payResult));
        return payResult;
    }
}
