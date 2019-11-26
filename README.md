# SSO 单点登录系统

### 项目介绍

SSO单点登录系统是基于Spring-Boot、oauth2.0协议、springSecurity、Mybatis、mysql、redis、jwtToken、Bootstrap、thymleaf等主流前后端技术的单点登录以及用户权限管理系统。基于spring oauth2.0协议研发的单点登录系统,可兼容多语言客户端接入,客户端遵循本系统的接入规范可实现单点登录和单点登出,
本系统支持跨域,可在不同域名下的子系统的单点登录/登出功能的实现。后端开发人员都可以参考优化此项目,也可以基于此系统开发和实现具体生产项目。代码完全开源。

#### 单点登录原理及oauth2.0协议认证授权原理
##### 单点登录原理图
![输入图片说明](https://github.com/noodlesGood/Authorization-server/blob/master/images/sso%E7%99%BB%E5%BD%95.png "单点登录")
##### 单点注销原理图
![](https://github.com/noodlesGood/Authorization-server/blob/master/images/sso%E5%8D%95%E7%82%B9%E6%B3%A8%E9%94%80.png "单点注销")
##### oauth2.0认证授权原理图 本sso系统基于此实现
         +--------+                               +-----------------+
     |        |--（A）------- 授权请求 -------->|                 |
     |        |                               | 资源所有者（用户） |
     |        |<-（B）------- 授权许可 ---------|                 |
     |        |                               +-----------------+
     |        |
     |        |                               +-----------------+
     |        |--（C）------- 授权许可 -------->|                 |
     | 客户端  |                               |  授权服务器（1    |
     |        |<-（D）----- Access Token ----）|                 |
     |        |                               +-----------------+
     |        |
     |        |                               +-----------------+
     |        |（-（E）---- Access Token ----->|                 |
     |        |                               |   资源服务器（2   |
     |        |<-（F）---- 获取受保护的资源 -----|                 |
     +--------+                               +-----------------+


#### 后端架构

##### 开发环境

IDE : Intellij IDEA 2019.2.3

JDK : JDK1.8.x

Maven : Maven 3.6.x

MySQL: MySQL 5.7.x

…

##### 技术选型

核心框架：Spring Boot 2.x

授权协议：oauth2.0

安全框架：Spring Security 

视图框架：Spring MVC 

持久层框架：MyBatis 、JPA

缓存数据库：redis

日志管理：SLF4J、Log4j

…

##### 项目结构

sso-server： sso服务端

sso-client1： 接入sso服务的示范客户端

authorization-jwtToken： 认证授权服务器的token使用jwtToken，示范用例，不影响sso Server

authorization-redis： 认证授权服务器的token使用redis存储，示范用例，不影响sso Server

authorization-mysql： 认证授权服务器的token使用mysql存储，示范用例，不影响sso Server

### 安装教程

##### 获取源码
获取后端源码，获取上面所列所有项目结构，将其拷贝放置到本地目录。

##### 导入工程
使用 IDEA导入 Maven 项目或者fork项目导入到IDEA，在此之前请确认已安装 JDK 和 Maven 工具。

##### 导入数据库
新建sso数据库，使用项目https://github.com/noodlesGood/Authorization-server/blob/master/spring-security-oauth2-authorization-mysql/src/main/resources/oauth2db.sql数据库文件初始化数据库。
修改 sso-server 下 application.yml 中的数据源配置信息为自己的数据库配置。

#### 启动系统

###### sso server模块 与 ssso client模块（sso服务端：sso-server，sso客户端：sso-client）

找到 sso-server 工程下的SsoServerApplication类中右键运行，启动sso 单点登录系统,此时单点登录系统即可运行:http://127.0.0.1:8080/。

找到 sso-client1 工程下的SsoClientApplication类中右键运行，启动接入ssoServer的演示客户端，127.0.0.1:8081/。

##### 单点登录登出效果演示

1.此时当访问客户端任意地址http://127.0.0.1:8081/时，子应用因为没有登录，会重定向到sso-server的登录页面：http://127.0.0.1:8080/sso/login

2.用户输入user/123456,登录成功后自动跳转回刚刚的客户端地址，完成单点登录。

3.当在同一浏览器的sso登录系统或已经接入sso的其他子系统点击退出按钮时，所有此浏览器的应用将共同注销退出。

例如:sso-server主页：http://localhost:8081/client/auth/index 点击退出,刷新客户端即显示当前已经是退出状态。

###### 权限管理模块（权限管理：mango-admin，备份还原：mango-backup）
找到 mango-admin 工程下的MangoAdminApplication， 启动项目，开启权限系统服务。
找到 mango-backup 工程下的MangoBackupApplication.java，启动项目，开启备份服务。

###### 其他示例模块（Spring Cloud示例模块，作为开发模板和范例，根据需要启动）
以下为Spring Cloud体系各种功能的实现范例，可以根据需要启动，后续扩展开发也可以作为参考和模板使用，具体使用教程请参考本书后面Spring Cloud系列教程的章节，关于Spring Cloud体系的各种功能模块都有详细的讲解和完整的案例实现。

这些示例模块包括：

mango-producer： 服务提供者示例，演示服务提供者的实现

mango-consumer： 服务消费者示例，演示服务消费者的实现

mango-hystrix： 服务熔断监控模块，演示熔断监控功能的实现

mango-zuul： API服务网关模块，演示API统一网关的实现

mango-config： 配置中心服务端，演示分布式配置中心的实现

##### 注意事项：
注册中心是基础服务，需要先安装Consul，找到mango-consul工程，根据安装说明安装Consul。

如果需要链路追踪服务，需要安装zipkin，找到mango-zipkin 工程，根据安装说明安装zipkin。

如果需要配置中心服务，需要安装rabbitMQ，找到mango-config 工程，根据安装说明安装rabbitMQ。


### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
