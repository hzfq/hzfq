### 0. 概述
1. 示例应用实现了Spring Boot集成OAuth2第三方登录认证
2. 示例使用的第三方登录平台为Gitlab，Gitlab采用私有化部署
3. 源代码：https://github.com/hzfq/hzfq/tree/master/hzfq-git
3. 示例应用采用授权码方式实现

### 1. 版本信息
1. jdk: 1.8+
2. Spring Boot: 2.6.2.RELEASE+
3. Spring Boot Starter OAuth2 Client: 2.6.2.RELEASE+

### 2. 依赖信息
```
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
 </dependency>
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-thymeleaf</artifactId>
 </dependency>

 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-oauth2-client</artifactId>
 </dependency>
````

### 3. Gitlab配置
1. Gitlab采用私有化部署，可使用Docker安装并启动
2. 访问地址：http://192.168.0.126/

### 4. 项目配置
1. 访问地址：http://192.168.0.104:6006/
2. 配置文件 application.properties
```
server.port=6006
spring.security.oauth2.client.provider.gitlab.authorization-uri=http://192.168.0.126/oauth/authorize
spring.security.oauth2.client.provider.gitlab.token-uri=http://192.168.0.126/oauth/token
spring.security.oauth2.client.provider.gitlab.jwk-set-uri=http://192.168.0.126/oauth/discovery/keys
#spring.security.oauth2.client.provider.gitlab.issuer-uri=http://192.168.0.126/oauth/discovery/keys
spring.security.oauth2.client.provider.gitlab.user-info-uri=http://192.168.0.126/api/v4/user
spring.security.oauth2.client.provider.gitlab.user-name-attribute=username
#spring.security.oauth2.client.provider.gitlab.user-info-authentication-method=http://192.168.0.126/oauth/discovery/keys

spring.security.oauth2.client.registration.gitlab.provider=gitlab
spring.security.oauth2.client.registration.gitlab.client-name=hzfq-git
spring.security.oauth2.client.registration.gitlab.client-id=ecf4968c343aec1432a3cf718202f04cfd4fdbcf63dedb41d01934f22aa53bb9
spring.security.oauth2.client.registration.gitlab.client-secret=c6c1ea6d52d53332d92f9c16cf91cc0a7298c0a9c1ed1f56bbedc5d0db74b13d
spring.security.oauth2.client.registration.gitlab.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.gitlab.redirect-uri=http://192.168.0.104:6006/login/oauth2/code/gitlab
spring.security.oauth2.client.registration.gitlab.scope=api+read_user
spring.security.oauth2.client.registration.gitlab.client-authentication-method=post
```
3. 配置修改
   3.1 gitab访问地址需修改为自己部署的地址
   3.2 gitlab重定向uri需要修改为本地启动项目的 IP 和 Port

### 5. 启动项目及访问
1. 浏览器打开 http://192.168.0.104:6006/ 将自动跳转到Gitlab登录和授权页面
2. 授权成功后跳转回项目首页
3. 自定义配置和扩展可查看代码注释

### 6. 参考资料
1. [Spring Security OAuth2 完全解析 (流程/原理/实战定制)](https://www.cnblogs.com/simpleito/p/15786122.html "Spring Security OAuth2 完全解析 (流程/原理/实战定制)")
2. [Spring Security OAuth2](https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html "Spring Security OAuth2")
3. [Gitlab Docker配置和启动](https://docs.gitlab.cn/jh/install/docker.html "Gitlab Docker配置和启动")