package com.noodlesGuo.ssoServer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 作为资源服务器的时候
 * RestController是不可以跟url的
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UerInfoController {

    @GetMapping("/me")
    public Principal getUser(Principal principal){
        log.info("userInfo:{}",principal);
        return principal;
    }
}
