package com.entrobus.credit.payment.services.impl;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.common.util.IpUtil;
import com.entrobus.credit.payment.config.WxPayProperties;
import com.entrobus.credit.payment.services.WeChatPayService;
import com.github.binarywang.wxpay.bean.request.WxEntPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxEntPayResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.apache.commons.lang3.RandomStringUtils;
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

    /**
     * 发送普通微信红包给指定用户
     * @param openId 微信用户OpenId
     * @param money 红包金额
     * @return WxPaySendRedpackResult
     * @throws WxPayException
     */
    @Override
    public WxPaySendRedpackResult sendRedpack(String openId, Integer money) throws WxPayException {
        WxPaySendRedpackRequest sendRedpackRequest = new WxPaySendRedpackRequest();
        sendRedpackRequest.setWxAppid(properties.getAppId());
        sendRedpackRequest.setMchId(properties.getMchId());//商户号
        sendRedpackRequest.setNonceStr(String.valueOf(System.currentTimeMillis()));//随机字符串
        sendRedpackRequest.setMchBillNo(RandomStringUtils.randomAlphanumeric(28));//订单号，不能超过28位
        sendRedpackRequest.setSendName("熵商科技");//红包发送者名称
        sendRedpackRequest.setReOpenid(openId);//接受红包的用户
        sendRedpackRequest.setTotalAmount(money);//付款金额，单位分
        sendRedpackRequest.setTotalNum(1); //红包发放总人数
        sendRedpackRequest.setWishing("社区贷顺利上线了，大家辛苦了，发个红包犒劳一下大家！");//祝福语
        sendRedpackRequest.setActName("福利发放");//活动名称
        sendRedpackRequest.setRemark("社区贷红包福利");
        // 微信客户端生成订单的IP地址
        String ip = IpUtil.getLocalIP();
        if (StringUtils.isBlank(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if(money > 20000){
            //发放红包使用场景，红包金额大于200时必传
            sendRedpackRequest.setSceneId("PRODUCT_4");
        }
        sendRedpackRequest.setClientIp(ip);//调用接口的机器Ip地址
        return wxService.sendRedpack(sendRedpackRequest);//发送微信红包
    }
}
