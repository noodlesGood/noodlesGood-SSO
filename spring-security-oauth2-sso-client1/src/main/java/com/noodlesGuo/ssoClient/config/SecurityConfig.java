package com.noodlesGuo.ssoClient.config;

import com.noodlesGuo.ssoClient.constants.SsoServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

/**
 * security配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SessionRegistry sessionRegistry;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/login","/auth/logout").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(SsoServerConstants.SSO_AUTHORIZE_URL)
            .and()
                .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry)
            .and()
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl(SsoServerConstants.SSO_Login_URL)
                    .permitAll()
             .and()
                .csrf().disable()
        ;
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }
}
