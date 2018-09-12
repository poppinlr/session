package com.zhuochen.spring.session.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;

@Component
@Log4j2
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    @Transactional
    public void onApplicationEvent(@Nonnull ApplicationReadyEvent applicationReadyEvent) {
        //TODO load all user data to redis
    }


}
