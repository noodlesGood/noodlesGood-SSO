package com.noodlesGuo.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
public class AuthorizationMysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationMysqlApplication.class,args);
    }
}
