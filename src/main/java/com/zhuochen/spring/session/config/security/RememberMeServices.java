package com.zhuochen.spring.session.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.stereotype.Component;

@Component
public class RememberMeServices extends SpringSessionRememberMeServices {



    @Override
    public void setAlwaysRemember(boolean alwaysRemember) {
        super.setAlwaysRemember(true);
    }
}
