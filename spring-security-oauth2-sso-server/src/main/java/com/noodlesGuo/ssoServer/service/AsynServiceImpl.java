package com.noodlesGuo.ssoServer.service;

import com.noodlesGuo.ssoServer.constants.SsoServerConstant;
import com.noodlesGuo.ssoServer.utils.HttpUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步相关service
 */
@Service
public class AsynServiceImpl {

    /**
     * 异步调用客户端注销接口
     * @param ssoSessionId
     */
    @Async
    public void callClientLogout(String logoutUrl,String ssoSessionId){
        String requestUrl = logoutUrl + "?"+ SsoServerConstant.SSO_SESSIONID + "=" + ssoSessionId;
        HttpUtil.get(requestUrl,null);
    }
}
