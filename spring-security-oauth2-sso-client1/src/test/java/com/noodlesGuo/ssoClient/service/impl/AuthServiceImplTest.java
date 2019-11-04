package com.noodlesGuo.ssoClient.service.impl;

import com.noodlesGuo.ssoClient.constants.SsoServerConstants;
import com.noodlesGuo.ssoClient.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class AuthServiceImplTest {

    @Test
    public void getAccessToken() {
        //code只能用一次
        String code ="npT4MA";
        String requestUrl = String.format(SsoServerConstants.SSO_TOKEN_URL, code, SsoServerConstants.CLIENT_ID
                , SsoServerConstants.CLIENT_SECRET,SsoServerConstants.REDIRECT_URL);
        String response = HttpUtil.post(requestUrl, null, null);
        log.info("【获取tokenj结果:{}】",response);
    }
}