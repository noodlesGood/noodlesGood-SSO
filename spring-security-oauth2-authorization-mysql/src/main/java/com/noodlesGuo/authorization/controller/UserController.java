package com.noodlesGuo.authorization.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 返回用户信息
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @GetMapping("/me")
    public Principal getUser(Principal principal){
        log.info("userInfo:{}",principal);
        return principal;
    }
}
