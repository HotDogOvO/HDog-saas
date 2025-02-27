# HDog-saas

## hotdog-saas-tob

### 功能说明

多租户小程序的B端功能

### 代码层级说明

系统采用DDD设计，具体说明如下

#### start

启动层

+ 只用来服务启动

#### web

接口层

+ 提供对外controller
+ 设置公共返回值
+ 全局异常处理
+ 签名、token校验

#### application

交互编排层

+ 对外接口统一（使用外观模式）
+ 业务编排（处理器模式）
+ model与DTO的转换

#### domain

领域层，属于最底层

+ 提供适配器接口（适配器模式）
+ 提供基础服务接口
+ 提供存储层接口
+ 提供领域对象
+ 提供常量、枚举、配置、工具类

#### infra

基础设施层

+ DO与model转换
+ 提供基础设施能力
    + 缓存
    + 权限
    + 定时任务
    + 消息队列
+ 存储层具体实现
+ 内/外部服务调用
+ 提供数据库对象
+ 项目初始化

### 代码结构

```
|- hdog-saas-tob
    |-> hdog-saas-start （启动层）
         |- src/main/java
              |- com.hotdog.saas (主包路径)
                   |- Application (启动类)
         |- src/test/java
              |- com.hotdog.saas (单元测试)
         |- resources
              |- banner.txt
              |- application.yml
         |-pom.xml
    |-> hdog-saas-web （接口层）
         |- src/main/java
              |- com.hotdog.saas.web (主包路径，上层)
                   |- advice (全局异常处理)
                   |- aspect (aop)
                   |- config (web层配置，包括接口文档、过滤器、拦截器等)
                   |- controller (RestAPI)
                   |- filter (过滤器) 
                   |- interceptor (拦截器，签名&token的校验) 
         |-pom.xml
    |-> hdog-saas-application （业务编排层，中间层）
         |- src/main/java
              |- com.hotdog.saas.application (主包路径)
                   |- annotation (自定义注解)
                   |- assembler (DO和DTO转换（使用mapstruct）)
                   |- entity (DTO)
                        |- request (请求参数)
                        |- response (响应参数)                 
                   |- facade（门面层，对web层提供接口）
                   |- processor（业务逻辑编排，一个接口对应一个processor）
                   |- template（模板层，提供processor通用模板）
         |-pom.xml
    |-> hdog-saas-domain （领域层，最底层）
         |- src/main/java
              |- com.hotdog.saas.domain (主包路径)
                   |- adapter (适配器接口)
                   |- config (系统配置，从配置文件中读取的)
                   |- constant (系统常量)
                   |- enums (系统枚举)
                   |- exception (系统异常)
                   |- foundation（基础模块，缓存、认证等）
                   |- model (领域模型)
                   |- repository (存储层接口)
                   |- service (业务实现接口)
                        |- impl (具体业务逻辑实现)
                   |- utils (系统工具类)
         |-pom.xml
    |-> hdog-saas-infra （基础设施层，依赖倒置domain层）
         |- src/main/java
              |- com.hotdog.saas.infra (主包路径)
                   |- adapter (feign适配器，参数转换)
                   |- converter (PO和DO转换（使用mapstruct）)
                   |- dao (MybatisPlus实现)
                   |- entity (DO，和数据库表一对一对应)
                   |- feign（服务调用）
                        |- config (feign配置、参数格式转换)
                        |- external (外部服务)
                        |- internal (内部服务)
                   |- foundation（基础模块，缓存、认证等）
                        |- file (文件上传)
                        |- kafka (消息队列)
                        |- task（定时任务）
                   |- init (系统初始化操作)
                        |- database (数据库升级)
                   |- repository (repository实现，做参数转换（DO转PO)，处理缓存）
         |- resources
              |- mapper（sql实现）
         |-pom.xml
    |-pom.xml（所有的项目工程的parent pom，管理jar版本、依赖）
```

### 数据库设计说明

#### 系统表

以【s_】开头的表

#### b端表（后台使用）

以【b_】开头的表

#### c端表

以【c_】开头的表
第二位单词表示业务，便于后面服务拆分
例：c_education_course（c端_教培类_课程表）

### 中间件说明

|  中间件   |    版本    |
|:------:|:--------:| 
| mysql  |  8.0.22  |
| redis  |  7.4.1   | 
| minio  |  latest  | 
| kafka  |  3.9.0   | 
| nacos  |  2.4.3   | 
| canal  |  1.1.7   | 

