package com.noodlesGuo.ssoClient.service;

import java.util.Map;

/**
 * 认证授权相关service
 */
public interface AuthService {
    public Map<String,Object> getAccessToken(String code);
    public Map<String,Object> getUserInfo(String accessToken);
}
