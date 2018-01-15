# 支付中心

#微信Java SDK开发文档
https://github.com/wechat-group/weixin-java-tools/wiki

#微信开发参考
http://blog.csdn.net/column/details/14826.html

#微信小程序开发文档
https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html#wxloginobject

#熵商微信公众号
微信公众平台（https://mp.weixin.qq.com/）
	公众号账户：dev@entrobus.com（在微信开放平台上面绑定需要用到）
	公众号密码 ：Shangshang2016（在微信开放平台上面绑定需要用到）
	原始ID（account_id）：gh_14b77e15d9c7
	AppID(应用ID)：wxac428c50aaccf942
	AppSecret(应用密钥)：34f7cb149373924005ec00abaceeeb1d
	微信支付商户号:1461196702
	商户平台登录帐号:1461196702@1461196702
	商户平台登录密码:276313
    API密钥：C644ED21DEB14FF6E909FCDA6B1B7BA8

熵商微信开放平台（https://open.weixin.qq.com）
huangxuyang@entrobus.com
Shangshang2016


#ngrok(内网穿透)
http://www.qydev.com/

#商户号登录URL
https://pay.weixin.qq.com/index.php/core/home/login

#熵商小程序
dev3@entrobus.com
shangshang2017

公司测试微信
18925136016
微信号：shangtest1
shangtest2016

#侯斯特第三方授权平台（微信公众号设置自动回复）
http://weixinhost.com/
登录账号：13822290843
登录密码：123456

#微信支付文档
https://pay.weixin.qq.com/wiki/doc/api/index.html

## maven 单独构建多模块项目中的单个模块
### 单独构建模块 payment-service，同时会构建 payment-service 模块依赖的其他模块
mvn install -pl payment-service -am