package com.noodlesGuo.ssoServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 基于内存的授权码存储扩展类
 */
@Service
public class SsoInMemoryAuthorizationCodeServices extends InMemoryAuthorizationCodeServices {
    @Autowired
    private HttpServletRequest request;
    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        String ssoSessionId = request.getSession().getId();
        ServletContext context = request.getServletContext();
        context.setAttribute(code,ssoSessionId);
        super.store(code, authentication);
    }
}
