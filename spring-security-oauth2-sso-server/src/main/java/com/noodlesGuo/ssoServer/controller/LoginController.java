package com.noodlesGuo.ssoServer.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆/登出controller
 */
@Controller
public class LoginController {

    @GetMapping(value = "login")
    public String login(){
        return "login/login";
    }

    /**
     * sso server 退出后的后续操作
     * @return
     */
    @RequestMapping("/asynLogout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("ceshi");
        return "redirect:/login";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
