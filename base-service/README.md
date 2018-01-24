## 基础数据服务

## maven 单独构建多模块项目中的单个模块
### 单独构建模块 base-service，同时会构建base-service模块依赖的其他模块
mvn install -pl base-service -am

### 单独构建模块 common，同时构建依赖模块common的其他模块
mvn install -pl common -am -amd

#打测试包
clean package -Ptest -pl base-service -am

#打生产包
clean package -Pprod -pl base-service -am