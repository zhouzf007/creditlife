# Monitor 应用服务监控


## 多个环境配置文件
在现实的开发环境中，我们需要不同的配置环境；格式为application-{profile}.properties，其中{profile}对应你的环境标识，比如：
application-test.properties：测试环境
application-dev.properties：开发环境
application-prod.properties：生产环境
使用方式：application.properties中设置spring.profiles.active=dev（使用开发环境配置）
如果是application.yml，使用方式：只需要在application.yml中加：
 spring:
  profiles:
    active: dev

## 使用测试环境的配置（test）启动项目
java -jar xxx.jar --spring.profiles.active=test
## 使用生产环境的配置（prod）启动项目
java -jar xxx.jar --spring.profiles.active=prod