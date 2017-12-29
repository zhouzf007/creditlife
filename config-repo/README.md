# 关于启用配置项加密
## 前置条件
需要替换{}/jre/lib/security 目录（windows有两个jre）下的local_policy.jar和US_export_policy.jar。可在jdk官网下载。
## RSA 非对称加密
### 生成秘钥文件 有效期1095天（自定义）
keytool -genkeypair -alias creditlife -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass Lee7edee9w -keystore creditlife-config.jks -storepass eiy6eeBief -validity 1095
## 加密解密
访问config server 提供的接口进行数据的加密解密，如下localhost:8888给为实际的config server地址
### 加密
使用postman post请求http://localhost:8888/encrypt，body为要加密的数据
### 解密
使用postman post请求http://localhost:8888/decrypt，body为要解密的数据