package com.zhuochen.spring.session.config.security;

import com.zhuochen.spring.session.service.security.CUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@EnableWebSecurity
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 10)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    @Autowired
    private AuthFailureHandler failureHandler;

    @Autowired
    private AuthSuccessHandler successHandler;

    @Autowired
    private CUserDetailService cUserDetailService;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private AuthAccessDeniedHandler accessDeniedHandler;

//    @Autowired
//    private AuthProvider authProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(cUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/auth").authenticated()
                .antMatchers("/admin").hasRole("ADMIN1")
                .and()
                .csrf().disable()

                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
//                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionAuthenticationFailureHandler(failureHandler)
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .maximumSessions(2)
                .maxSessionsPreventsLogin(false)//false之后登录踢掉之前登录,true则不允许之后登录
                .sessionRegistry(this.sessionRegistry());
    }

    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
    }
}
