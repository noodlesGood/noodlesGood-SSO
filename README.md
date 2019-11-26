# SSO 单点登录系统 基于 oauth2.0 
利用spring oauth2.0协议搭建认证、授权服务器实现sso server 端的搭建
## 如果只对单点登录系统
## -原理流程图:
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

## SSO 单点登录系统
#### 项目介绍
java 版SSO单点系统、基于spring oauth2.0协议研发的单点登录系统,可兼容多语言客户端接入,客户端遵循本系统的接入规范可实现单点登录和单点登出,
本系统解决了实现了不同域名下的子系统的单点登录功能的实现。
代码完全开源。
 
# SSO 单点登录系统

### 项目介绍

SSO单点登录系统是基于Spring-Boot、oauth2.0协议、springSecurity、Mybatis、mysql、redis、jwtToken、Bootstrap、thymleaf等主流前后端技术的单点登录以及用户权限管理系统。基于spring oauth2.0协议研发的单点登录系统,可兼容多语言客户端接入,客户端遵循本系统的接入规范可实现单点登录和单点登出,
本系统支持跨域,可在不同域名下的子系统的单点登录/登出功能的实现。后端开发人员都可以参考优化此项目,也可以基于此系统开发和实现具体生产项目。代码完全开源。

#### 单点登录原理及oauth2.0协议认证授权原理

![输入图片说明](https://github.com/noodlesGood/Authorization-server/blob/master/images/sso%E7%99%BB%E5%BD%95.png "单点登录")
![](https://github.com/noodlesGood/Authorization-server/blob/master/images/sso%E5%8D%95%E7%82%B9%E6%B3%A8%E9%94%80.png "单点注销")

#### 书籍链接

##### 京东 参考链接：

[https://item.jd.com/49029589963.html](https://item.jd.com/49029589963.html)

[https://item.jd.com/48751078393.html](https://item.jd.com/48751078393.html)

##### 当当 参考链接：

[http://product.dangdang.com/1438252364.html](http://product.dangdang.com/1438252364.html)

##### 天猫 参考链接：

[https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.16.a4721049QUevoi&id=595707200013](https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.16.a4721049QUevoi&id=595707200013)

[https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.6.a4721049QUevoi&id=596116466076](https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.6.a4721049QUevoi&id=596116466076)


### 技术交流

为了方便大家提问和技术交流，整了个QQ群，欢迎童鞋们加入。

QQ技术交流群： 429854222

笔者技术博客：[https://www.cnblogs.com/xifengxiaoma/](https://www.cnblogs.com/xifengxiaoma/)

### 功能列表


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

安全框架：Spring Security 5.x

视图框架：Spring MVC 5.x

持久层框架：MyBatis 3.x 、JPA

缓存数据库：redis

日志管理：SLF4J、Log4j

…

##### 项目结构

mango-common： 公共代码模块，主要放置一些工具类

mango-core： 封装业务模块，主要封装公共业务模块

mango-admin： 后台管理模块，包含用户、角色、菜单管理等

mango-backup： 系统数据备份还原模块，可选择独立部署

mango-monitor： 系统监控服务端，监控Spring Boot应用

mango-producer： 服务提供者示例，方便在此基础上搭建模块

mango-consumer： 服务消费者示例，方便在此基础上搭建模块

mango-hystrix： 服务熔断监控模块，收集汇总熔断统计信息

mango-zuul： API服务网关模块，统一管理和转发外部调用请求

mango-config： 配置中心服务端，生成GIT配置文件的访问接口

mango-consul： 注册中心，安装说明目录，内附安装引导说明

mango-zipkin： 链路追踪，安装说明目录，内附安装引导说明

config-repo： 配置中心仓库，在GIT上统一存储系统配置文件

mango-pom： 聚合模块，仅为简化打包，一键执行打包所有模块

### 安装教程


#### 后端安装

##### 获取源码
获取后端源码，获取上面所列所有项目结构，将其拷贝放置到本地目录。

##### 导入工程
使用 IDEA导入 Maven 项目，在此之前请确认已安装 JDK 和 Maven 工具。

##### 编译源码
找到 mango-pom 工程下的 pom.xml，执行 maven clean install 命令进行一键打包。
一般来说不会有什么问题，如果还打包失败，可以按照优先级逐个编译试一试。

##### 导入数据库
新建mango数据库，使用项目sql目录下的mango.sql 脚本，导入初始化数据库。
修改 mango-admin 下 application.yml 中的数据源配置信息为自己的数据库配置。
修改 mango-backup下 application.yml 中的数据源配置信息为自己的数据库配置。

##### 启动系统

###### 基础必需模块（注册中心：mango-consul，服务监控：mango-monitor）

找到 mango-consul 工程，根据安装说明安装注册中心，执行 consul agent -dev 启动。
找到 mango-monitor 工程下的MangoMonitorApplication， 启动项目，开启服务监控。

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
