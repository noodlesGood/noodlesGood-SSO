package com.noodlesGuo.ssoClient.constants;

/**
 * sso服务器静态配置相关
 */
public class SsoServerConstants {

    /**SSO SERVER*/
    public static final String SSO_BASE_URL = "http://127.0.0.1:8080/sso";
    public static final String SSO_AUTHORIZE_URL = SSO_BASE_URL+"/oauth/authorize?response_type=code&client_id="+SsoServerConstants.CLIENT_ID+
            "&redirect_uri="+SsoServerConstants.REDIRECT_URL+"&scope=all";
    public static final String SSO_TOKEN_URL = SSO_BASE_URL+"/oauth/token?grant_type=authorization_code" +
            "&code=%s&client_id=%s&client_secret=%s&redirect_uri=%s";

    /**SSO CLIENT*/
    public static final String REDIRECT_URL = "http://localhost:8081/client/auth/getCode";
    public static final String CLIENT_ID = "client1";
    public static final String CLIENT_SECRET = "oauth1";

}
