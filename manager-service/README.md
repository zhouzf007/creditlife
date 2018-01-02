## 系统管理后台服务

## maven 单独构建多模块项目中的单个模块
### 单独构建模块 manager-service，同时会构建 manager-service 模块依赖的其他模块
mvn install -pl manager-service -am

### 单独构建模块 common，同时构建依赖模块 newseax-common 的其他模块
mvn install -pl common -am -amd
