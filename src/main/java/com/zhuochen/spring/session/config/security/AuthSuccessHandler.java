package com.zhuochen.spring.session.config.security;

import com.zhuochen.spring.session.jpa.entity.security.UserDetailDataEntity;
import com.zhuochen.spring.session.jpa.entity.security.UserRoleDataEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<UserRoleDataEntity> roleDataEntityList = (List<UserRoleDataEntity>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        UserDetailDataEntity entity = (UserDetailDataEntity) SecurityContextHolder.getContext().getAuthentication().getDetails();
        log.error("AuthSuccessHandler:--------------", authentication);
        log.info(httpServletResponse.getHeaderNames().parallelStream().collect(Collectors.joining()));
        log.info(httpServletRequest.getSession().getId());
    }
}
