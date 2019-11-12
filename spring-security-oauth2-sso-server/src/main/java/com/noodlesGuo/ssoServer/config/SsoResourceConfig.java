package com.noodlesGuo.ssoServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
@Configuration
@EnableResourceServer
@Order(3)
public class SsoResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
//
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/Login**",
                        "/LoginCheck**",
                        "/home/**",
                        "/Home/**",
                        "/Logout**"
                )
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutUrl("/Logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/Login?logoutOk")
                .invalidateHttpSession(true)
                .permitAll();
    }
}
