package com.noodlesGuo.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.time.Duration;

/**
 * oauth2授权服务配置类
 */
@Configuration
@EnableAuthorizationServer//开启授权服务
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;//配置数据源

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //基于内存的client
//        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
//        builder.withClient("client1")
//                .secret("$2a$10$5ZqTPJbt6PBMmEWUCu7v7uqPapBJsdAE5qFcY2ZE5dnXcGOA1Age6")
//                .resourceIds("oauth1")
//                .authorizedGrantTypes("password","authorization_code","refresh_token")
//                .authorities("ROLE_ADMIN","ROLE_USER")
//                .scopes("all")
//                .accessTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
//                .refreshTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
//                .redirectUris("http://www.baidu.com/");
        //基于数据库查询，实现客户端的数据库存储
        clients.withClientDetails(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jdbcTokenStore());
    }

    /**
     * 声明clientDetail的实现
     * @return JdbcClientDetailsService
     */
    @Bean
    public ClientDetailsService clientDetails(){
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 声明 JdbcTokenStore 的实现
     * @return JdbcTokenStore
     */
    @Bean
    public TokenStore jdbcTokenStore(){
        return new JdbcTokenStore(dataSource);
    }
}
