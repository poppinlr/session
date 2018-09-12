//package com.zhuochen.spring.session.config;
//
//import com.zhuochen.spring.session.config.interceptor.AuthorityInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private AuthorityInterceptor authorityInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authorityInterceptor)
//                .excludePathPatterns("/", "/swagger**/**", "/v2/api-docs**", "/error", "/login", "/logout");
//    }
//}
