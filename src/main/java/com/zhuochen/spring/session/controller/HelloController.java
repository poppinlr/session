package com.zhuochen.spring.session.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Log4j2
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(HttpServletRequest request) {
        log.info(request.getHeader("User-Agent"));
        log.info(request.getLocalAddr());
        log.info(request.getRemoteHost());
        log.info(request.getRemoteAddr());
        return "hello";
    }

    @GetMapping(value = "/auth")
    public String auth(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(request.getSession().getId());

        return authentication.toString();
    }

    @GetMapping(value = "/admin")
    public String admin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.toString();
    }

}
