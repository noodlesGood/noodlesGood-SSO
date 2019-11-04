package com.noodleaGuo.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 自定义令牌增强其，用以在jwtToken返回额外的信息
 */
@Component
public class AuthTokenEnhancer implements TokenEnhancer {
    @Autowired
    private HttpSession httpSession;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        HashMap<String, Object> infoMap = new HashMap<>();
        String userName = authentication.getName();
        String code = authentication.getOAuth2Request().getRequestParameters().get("code");
        String sessionId = httpSession.getId();
        infoMap.put("SessionId",sessionId);
        infoMap.put("UserName",userName);
        infoMap.put("code",code);
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(infoMap);
        return accessToken;
    }
}
