## 系统管理后台服务

## maven 单独构建多模块项目中的单个模块
### 单独构建模块 manager-service，同时会构建 manager-service 模块依赖的其他模块
mvn install -pl manager-service -am

### 单独构建模块 common，同时构建依赖模块 newseax-common 的其他模块
mvn install -pl common -am -amd

#打测试包
clean package -Ptest -pl manager-service -am

#打生产包
clean package -Pprod -pl manager-service -am

## springboot中通过cors协议解决跨域问题
https://www.cnblogs.com/520playboy/p/7306008.html

## 测试服务器Nginx目录 
服务器IP地址：119.29.119.138
/usr/local/data/nginx

##  Spring Boot 允许跨域请求、自定义请求头
http://blog.csdn.net/qq_19260029/article/details/78870330