package com.noodlesGuo.ssoClient.config;

import com.noodlesGuo.ssoClient.constants.SsoServerConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/getCode").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(SsoServerConstants.SSO_AUTHORIZE_URL);
    }
}
