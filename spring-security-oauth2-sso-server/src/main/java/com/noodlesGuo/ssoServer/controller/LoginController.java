package com.noodlesGuo.ssoServer.controller;

import com.noodlesGuo.ssoServer.entity.OauthClientDetails;
import com.noodlesGuo.ssoServer.service.AsynServiceImpl;
import com.noodlesGuo.ssoServer.service.OauthClientDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登陆/登出controller
 */
@Controller
public class LoginController {
    @Autowired
    private OauthClientDetailServiceImpl oauthClientDetailService;
    @Autowired
    private AsynServiceImpl asynService;

    @GetMapping(value = "login")
    public String login(){
        return "login/login";
    }

    /**
     * sso server 退出后的后续操作
     * @return
     */
    @RequestMapping("/ssoLogout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        List<OauthClientDetails> details = oauthClientDetailService.getList();
        System.out.println(details.size());
        for (OauthClientDetails detail:details) {
            asynService.callClientLogout(detail.getWebClientLogoutUri(),session.getId());
        }
        return "redirect:/login";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
