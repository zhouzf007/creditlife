package com.entrobus.credit.payment.wechat;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.net.URL;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

/**
 * 微信支付配置对象
 * Created by gacl on 2017/8/3.
 */
public class WechatPayConfig implements Serializable {

    /**
     * 构造方法
     * @param appId
     * @param mchId
     * @param partnerKey
     */
    public WechatPayConfig(String appId, String mchId, String partnerKey){
        this.appId = appId;
        this.mchId = mchId;
        this.partnerKey = partnerKey;
        if(StringUtils.isEmpty(appId) || StringUtils.isEmpty(mchId) || StringUtils.isEmpty(partnerKey)){
            throw new RuntimeException("初始化WeixinPayConfig对象时，appId，mchId，partnerKey是必须传递的参数，不能为空！");
        }
    }

    /////////////////////////////微信支付必要配置信息/////////////////////////////////////////////////////////
    //应用ID
    private  String appId;
    //微信支付商户号
    private  String mchId;
    //微信商户号付款API安全密钥
    private  String partnerKey ;
    //支付类型(取值如下：JSAPI，NATIVE，APP，必要参数)
    private String tradeType;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 访问需要证书的Https API接口时CA证书路径
     */
    private String sslContextFilePath;

    /**
     * sslContext上下文
     */
    private SSLContext sslContext;

    /////////////////////////////微信支付API常量列表/////////////////////////////////////////////////////////
    //1.微信统一下单API地址
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //2.微信查询订单API地址
    public static final String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    //3.关闭订单API地址
    public static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
    //4.申请退款API地址
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    //5.查询退款API地址
    public static final String REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
    //6.微信下载对账单API地址
    public static final String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    //7.企业付款API地址
    public static final String ENTERPRISE_TRANSFER_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    //8.查询企业付款API
    public static final String GET_TRANSFERINFO_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
    //9.发放普通红包URL
    public static final String SEND_REDPACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack" ;
    //10.发放列变红包URL
    public static final String SEND_GROUPREDPACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack" ;

    /////////////////////////////微信支付API列表/////////////////////////////////////////////////////////
    //1.微信统一下单API地址
    private  String unifiedOrderUrl;
    //2.微信查询订单API地址
    private  String orderQueryUrl;
    //3.关闭订单API地址
    private  String closeOrderUrl;
    //4.申请退款API地址
    private  String refundUrl;
    //5.查询退款API地址
    private  String refundQueryUrl;
    //6.微信下载对账单API地址
    private  String downloadBillUrl;
    //7.企业付款API地址
    private  String enterpriseTransferUrl;
    //8.查询企业付款API
    private  String getTransferinfoUrl;
    //9.微信支付成功回调通知URL
    private  String payNotifyUrl;
    //10.发放普通红包URL
    private  String sendRedpackUrl ;
    //11.发放列变红包URL
    private  String sendGroupRedpackUrl ;
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getPartnerKey() {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getUnifiedOrderUrl() {
        if(StringUtils.isNotEmpty(unifiedOrderUrl)){
            return unifiedOrderUrl;
        }else{
            return UNIFIED_ORDER_URL;
        }
    }

    public void setUnifiedOrderUrl(String unifiedOrderUrl) {
        this.unifiedOrderUrl = unifiedOrderUrl;
    }

    public String getOrderQueryUrl() {
        if(StringUtils.isNotEmpty(orderQueryUrl)){
            return orderQueryUrl;
        }else{
            return ORDER_QUERY_URL;
        }
    }

    public void setOrderQueryUrl(String orderQueryUrl) {
        this.orderQueryUrl = orderQueryUrl;
    }

    public String getCloseOrderUrl() {
        if(StringUtils.isNotEmpty(closeOrderUrl)){
            return closeOrderUrl;
        }else {
            return CLOSE_ORDER_URL;
        }
    }

    public void setCloseOrderUrl(String closeOrderUrl) {
        this.closeOrderUrl = closeOrderUrl;
    }

    public String getRefundUrl() {
        if(StringUtils.isNotEmpty(refundUrl)){
            return refundUrl;
        }else {
            return REFUND_URL;
        }
    }

    public void setRefundUrl(String refundUrl) {
        this.refundUrl = refundUrl;
    }

    public String getRefundQueryUrl() {
        if(StringUtils.isNotEmpty(refundQueryUrl)){
            return refundQueryUrl;
        }else {
            return REFUND_QUERY_URL;
        }
    }

    public void setRefundQueryUrl(String refundQueryUrl) {
        this.refundQueryUrl = refundQueryUrl;
    }

    public String getDownloadBillUrl() {
        if(StringUtils.isNotEmpty(downloadBillUrl)){
            return downloadBillUrl;
        }else {
            return DOWNLOAD_BILL_URL;
        }
    }

    public void setDownloadBillUrl(String downloadBillUrl) {
        this.downloadBillUrl = downloadBillUrl;
    }

    public String getEnterpriseTransferUrl() {
        if(StringUtils.isNotEmpty(enterpriseTransferUrl)){
            return enterpriseTransferUrl;
        }else {
            return ENTERPRISE_TRANSFER_URL;
        }
    }

    public void setEnterpriseTransferUrl(String enterpriseTransferUrl) {
        this.enterpriseTransferUrl = enterpriseTransferUrl;
    }

    public String getGetTransferinfoUrl() {
        if(StringUtils.isNotEmpty(getTransferinfoUrl)){
            return getTransferinfoUrl;
        }else {
            return GET_TRANSFERINFO_URL;
        }
    }

    public void setGetTransferinfoUrl(String getTransferinfoUrl) {
        this.getTransferinfoUrl = getTransferinfoUrl;
    }

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }

    public String getSendRedpackUrl() {
        if(StringUtils.isNotEmpty(sendRedpackUrl)){
            return sendRedpackUrl;
        }else {
            return SEND_REDPACK_URL;
        }
    }

    public void setSendRedpackUrl(String sendRedpackUrl) {
        this.sendRedpackUrl = sendRedpackUrl;
    }

    public String getSendGroupRedpackUrl() {
        if(StringUtils.isNotEmpty(sendGroupRedpackUrl)){
            return sendGroupRedpackUrl;
        }else {
            return SEND_GROUPREDPACK_URL;
        }
    }

    public void setSendGroupRedpackUrl(String sendGroupRedpackUrl) {
        this.sendGroupRedpackUrl = sendGroupRedpackUrl;
    }

    public String getSslContextFilePath() {
        return sslContextFilePath;
    }

    public void setSslContextFilePath(String sslContextFilePath) {
        if(StringUtils.isNotEmpty(sslContextFilePath)){
            ClassLoader classLoader = WechatPayConfig.class.getClassLoader();
            URL url = classLoader.getResource(sslContextFilePath);
            String filePath =  url.getFile();//设置apiclient_cert.p12配置文件的路径
            File file = new File(filePath);
            if(!file.exists()) {
                throw new RuntimeException("证书文件：【" + file.getPath() + "】不存在！");
            }else {
                try {
                    FileInputStream e = new FileInputStream(file);
                    KeyStore keystore = KeyStore.getInstance("PKCS12");
                    char[] partnerId2charArray = this.mchId.toCharArray();
                    //加载证书
                    keystore.load(e, partnerId2charArray);
                    this.sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
                } catch (Exception var6) {
                    throw new RuntimeException("证书文件有问题，请核实！", var6);
                }
            }
            this.sslContextFilePath =filePath;
        }
    }

    public SSLContext getSslContext() {
        if(sslContext != null){
            return sslContext;
        }else {
            try {
                return SSLContext.getDefault();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void setSslContext(SSLContext sslContext) {
        this.sslContext = sslContext;
    }
}
