package com.zhuochen.spring.session.config.security.session;

import com.zhuochen.spring.session.SessionApplication;
import com.zhuochen.spring.session.config.security.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityInitializer() {
        super(SecurityConfig.class, SessionApplication.class);
    }
}
