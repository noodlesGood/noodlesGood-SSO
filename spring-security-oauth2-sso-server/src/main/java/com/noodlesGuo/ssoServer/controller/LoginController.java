package com.noodlesGuo.ssoServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登陆/登出controller
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }
}
