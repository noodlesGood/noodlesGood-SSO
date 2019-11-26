# SSO 单点登录系统

### 项目介绍

SSO单点登录系统是基于Spring-Boot、oauth2.0协议、springSecurity、Mybatis、mysql、redis、jwtToken、Bootstrap、thymleaf等主流前后端技术的单点登录以及用户权限管理系统。基于spring oauth2.0协议研发的单点登录系统,可兼容多语言客户端接入,客户端遵循本系统的接入规范可实现单点登录和单点登出,
本系统支持跨域,可在不同域名下的子系统的单点登录/登出功能的实现。后端开发人员都可以参考优化此项目,也可以基于此系统开发和实现具体生产项目。代码完全开源。
###### 下面会有演示说明,和sso服务接入文档请感兴趣的朋友参考。

#### 单点登录原理及oauth2.0协议认证授权原理
##### 单点登录原理图
![输入图片说明](https://github.com/noodlesGood/Authorization-server/blob/master/images/sso%E7%99%BB%E5%BD%95.png "单点登录")
##### 单点注销原理图
![](https://github.com/noodlesGood/Authorization-server/blob/master/images/sso%E5%8D%95%E7%82%B9%E6%B3%A8%E9%94%80.png "单点注销")
##### oauth2.0认证授权原理图 本sso系统基于oauth2.0授权码实现
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
新建sso数据库，使用项目spring-security-oauth2-authorization-mysql/src/main/resources/oauth2db.sql数据库文件初始化数据库。

修改 sso-server 下 application.yml 中的数据源配置信息为自己的数据库配置。

#### 启动系统

###### sso server模块 与 ssso client模块（sso服务端：sso-server，sso客户端：sso-client）

找到 sso-server 工程下的SsoServerApplication类中右键运行，启动sso 单点登录系统,此时单点登录系统即可运行:http://127.0.0.1:8080/。

找到 sso-client1 工程下的SsoClientApplication类中右键运行，启动接入ssoServer的演示客户端，127.0.0.1:8081/。

## 单点登录登出效果演示

***1.此时当访问客户端任意地址http://127.0.0.1:8081/时，子应用因为没有登录，会重定向到sso-server的登录页面：http://127.0.0.1:8080/sso/login***

***2.用户输入user/123456,登录成功后自动跳转回刚刚的客户端地址，完成单点登录。***

***3.当在同一浏览器的sso登录系统或已经接入sso的其他子系统点击退出按钮时，所有此浏览器的应用将共同注销退出。***

***例如:sso-server主页：http://localhost:8081/client/auth/index 点击退出,刷新客户端即显示当前已经是退出状态。***

## sso服务接入文档
  ###### sso系统基于oauth2.0授权码模式,类似于微信第三方授权,不熟悉的小伙伴，可以先去了解下，有利于大家理解接入文档
  **1.首先当用户访问子应用时，子应用判断当前session的状态是否已登录,若不存在session或session状态不正确,则重定向到sso-server单点登录系统的授权地址**
  
  
 ---- 授权地址:SSO_SERVER_URL+"/oauth/authorize?response_type=code&client_id="+CLIENT_ID+
            "&redirect_uri="+CLIENT_REDIRECT_URL+"&scope=all
          
  ###### SSO_SERVER_URL:sso服务端地址 || CLIENT_ID:sso服务端分配给子应用的client_id,参考表oauth_client_details client_id字段
  
  ###### CLIENT_REDIRECT_URL:客户端子应用的回调地址,用于接收授权码,参考表oauth_client_details: web_server_redirect_uri字段
  
  **2.重定向到sso服务端的授权地址后，若当前已登录则sso服务端会直接回调子应用的注册的回调地址并返回授权code。**
  
  ----例如CLIENT_REDIRECT_URL?code=xxxx。若用户未登录，则正常登录后在跳转回调地址并返回授权code。
  
  **3.子应用的利用CLIENT_REDIRECT_URL回调地址获取授权码后根据授权code获取access_token。**
  
  ---请求方法:POST
  
  ---获取token的请求地址:SSO_BASE_URL+"/oauth/token?grant_type=authorization_code" +
            "&code=xxx&client_id=xxx&client_secret=xxx&redirect_uri=xxx"
            
  ---返回结果json：
         ```
             {
                    "access_token":"eyJhbGciOiJIU58M",
                    "refresh_token":"eyJhbGciO",
                    "scope":"all",
                    "token_type":"bearer",
                    "ssoSessionId":"F01EEE15D7AAA58EA1BBB268EB52EE29",
                    "expires_in":2591999
             }
         ```
         
****注意保存返回结果中的ssoSessionId的值，这是全局session的唯一标识，共通退出时会用到，务必保存（真正的jwtToken很长，这里只是示例）****
                
            
   ###### code:上一步取的授权code || client_id:sso服务端分配给子应用的client_id,参考表oauth_client_details client_id字段
   
   ###### client_secret:sso服务端分配给子应用的client_secret || redirect_uri:客户端注册的回调地址，必须与上一步的回调地址保持一致

**4.子应用根据请求的access_token,获取用户信息以及登录状态。**

---请求方法:GET

---请求头(header)：Authorization:"Bearer "+access_token (注意Bearer 后面有个空格)

---请求地址:SSO_BASE_URL+"/user/me

---返回结果json:
         ```
             {
                    "access_token":"eyJhbGciOiJIU58M",
                    "refresh_token":"eyJhbGciO",
                    "scope":"all",
                    "token_type":"bearer",
                    "ssoSessionId":"F01EEE15D7AAA58EA1BBB268EB52EE29",
                    "expires_in":2591999
             }



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
