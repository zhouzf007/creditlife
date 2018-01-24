## 网关

## maven 单独构建多模块项目中的单个模块
### 单独构建模块 gateway，同时会构建 gateway 模块依赖的其他模块
mvn install -pl gateway -am

### 单独构建模块 common，同时构建依赖模块 newseax-common 的其他模块
mvn install -pl common -am -amd

#打测试包
clean package -Ptest -pl gateway -am

#打生产包
clean package -Pprod -pl gateway -am