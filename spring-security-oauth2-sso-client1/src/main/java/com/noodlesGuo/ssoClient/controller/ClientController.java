package com.noodlesGuo.ssoClient.controller;

import com.noodlesGuo.ssoClient.constants.SsoServerConstants;
import com.noodlesGuo.ssoClient.service.AuthService;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private ServletContext context;
    @GetMapping("/login")
    public String getCode(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        log.info("resultCode:{}",code);
        Map<String, Object> accessTokenResult = authService.getAccessToken(code);
        String ssoSessionId = (String) accessTokenResult.get("ssoSessionId");
        log.info("【token请求的结果：】{}",accessTokenResult);
        String accessToken = (String) accessTokenResult.get("access_token");
        Map<String, Object> userInfo = authService.getUserInfo(accessToken);
        log.info("【返回的user：】{}",userInfo);
        Map<String, Object> principalMap = parseUserPrincipal(userInfo);
        Boolean authenticated = (Boolean) principalMap.get("authenticated");
        if (authenticated){//判断当前用户是否在sso Server已经认证
            ArrayList<GrantedAuthority>  authorities = (ArrayList<GrantedAuthority>)principalMap.get("authorities");
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principalMap.get("principal"), null,authorities);
            HttpSession session = request.getSession();
            log.info("【client_sessionId:】{}",session.getId());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            context.setAttribute(ssoSessionId,session.getId());
            sessionRegistry.registerNewSession(session.getId(),session);
        }
        return  "redirect:/auth/index";
    }

    @GetMapping("/index")
    public String authenticate(){
        return "index";

    }

    /**
     * 客户端注销回调接口,由sso server端调用
     * @return String
     */
    @GetMapping("/Logout")
    public void logout(HttpServletRequest request,HttpServletResponse response){
        String ssoSessionId = request.getParameter(SsoServerConstants.SSO_SESSIONID);
        String sessionId = (String) context.getAttribute(ssoSessionId);
        System.out.println("id:"+sessionId);
        if (sessionId!=null){
            SessionInformation sessionInformation = sessionRegistry.getSessionInformation(sessionId);
            System.out.println("sessionInformation:"+sessionInformation);
            if (sessionInformation!=null){
                sessionInformation.expireNow();
            }
            context.removeAttribute(ssoSessionId);
        }
    }

    /**
     * 解析返回的principal
     * @param userPrincipal
     * @return Map
     */
    private Map<String,Object> parseUserPrincipal(Map<String,Object> userPrincipal){
        Map<String, Object> resultMap = new HashMap<>();
        Boolean authenticated = (Boolean) userPrincipal.get("authenticated");
        String principal = (String) userPrincipal.get("principal");
        Map<String,Object> userAuthentication = (Map<String, Object>) userPrincipal.get("userAuthentication");
        List<Map<String,String>> authorities = (List<Map<String, String>>) userAuthentication.get("authorities");
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Map<String,String> authoritie:authorities) {
            String authority = authoritie.get("authority");
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        resultMap.put("authenticated",authenticated);
        resultMap.put("principal",principal);
        resultMap.put("authorities",grantedAuthorities);
        return resultMap;
    }
}
