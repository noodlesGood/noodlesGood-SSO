package com.noodlesGuo.ssoServer.config;

import com.noodlesGuo.ssoServer.constants.SsoServerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义令牌增强器，返回扩展信息
 */
public class SsoTokenEnhancer implements TokenEnhancer {
    @Autowired
    private HttpServletRequest request;
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String code = authentication.getOAuth2Request().getRequestParameters().get("code");
        ServletContext context = request.getServletContext();
        String ssoSessionId = (String) context.getAttribute(code);
        Map<String, Object> extendMap = new HashMap<>();
        extendMap.put(SsoServerConstant.SSO_SESSIONID,ssoSessionId);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(extendMap);
        context.removeAttribute(code);
        return accessToken;
    }
}
