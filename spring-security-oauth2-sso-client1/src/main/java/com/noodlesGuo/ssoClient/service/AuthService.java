package com.noodlesGuo.ssoClient.service;

/**
 * 认证授权相关service
 */
public interface AuthService {
    public String getAccessToken(String code);
}
