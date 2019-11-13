package com.noodlesGuo.ssoClient.controller;

import com.noodlesGuo.ssoClient.service.AuthService;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 客户端登陆/退出controller
 */
@Controller
@RequestMapping("/auth")
@Slf4j
public class ClientController {
    @Autowired
    private AuthService authService;
    @GetMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        log.info("resultCode:{}",code);
        Map<String, Object> result = authService.getAccessToken(code);
        log.info("【token请求的结果：】{}",result);
        String accessToken = (String) result.get("access_token");
        Map<String, Object> userInfo = authService.getUserInfo(accessToken);
        log.info("【返回的user：】{}",userInfo);
    }
}
