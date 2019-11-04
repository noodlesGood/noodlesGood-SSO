package com.noodlesGuo.ssoServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security配置
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/oauth/authorize","/sso/oauth/token").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").loginProcessingUrl("/login")// 设置登录页地址，并设置处理登录请求的路径
                .and()
                    .httpBasic().disable()
               ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置基于内存的登录用户
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user")
                .password(passwordEncoder().encode("123456"))
                .roles("USER");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**","/css/**", "/static/**", "/images/**", "/**/favicon.ico");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
