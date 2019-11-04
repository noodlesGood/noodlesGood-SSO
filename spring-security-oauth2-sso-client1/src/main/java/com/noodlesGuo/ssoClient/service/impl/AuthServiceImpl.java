package com.noodlesGuo.ssoClient.service.impl;

import com.noodlesGuo.ssoClient.constants.SsoServerConstants;
import com.noodlesGuo.ssoClient.service.AuthService;
import com.noodlesGuo.ssoClient.utils.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * 获取认证授权的service
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String getAccessToken(String code) {
        String requestUrl = String.format(SsoServerConstants.SSO_TOKEN_URL, code, SsoServerConstants.CLIENT_ID
                , SsoServerConstants.CLIENT_SECRET,SsoServerConstants.REDIRECT_URL);
        String response = HttpUtil.post(requestUrl, null, null);
        return null;
    }
}
