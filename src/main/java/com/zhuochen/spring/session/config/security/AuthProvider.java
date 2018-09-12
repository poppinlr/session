//package com.zhuochen.spring.session.config.security;
//
//import com.zhuochen.spring.session.service.security.CUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {
//
//    @Autowired
//    private CUserDetailService userDetailService;
//    private PasswordEncoder passwordEncoder;
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        if (authentication.getCredentials() == null) {
//            this.logger.debug("Authentication failed: no credentials provided");
//            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
//        } else {
//            String presentedPassword = authentication.getCredentials().toString();
//            if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
//                this.logger.debug("Authentication failed: password does not match stored value");
//                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
//            }
//        }
//    }
//
//    @Override
//    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
//        try {
//            UserDetails loadedUser = this.userDetailsService.loadUserByUsername(username);
//            if (loadedUser == null) {
//                throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
//            } else {
//                return loadedUser;
//            }
//        } catch (AuthenticationException var4) {
//            return null;
//        }
//    }
//}
