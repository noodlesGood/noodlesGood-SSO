package com.noodlesGuo.ssoClient.controller;

import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 客户端登陆/退出controller
 */
@Controller
@RequestMapping("/auth")
@Slf4j
public class ClientController {
    @GetMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        log.info("resultCode:{}",code);
    }
}
