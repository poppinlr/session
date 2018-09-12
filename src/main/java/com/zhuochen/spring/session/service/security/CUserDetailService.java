package com.zhuochen.spring.session.service.security;

import com.zhuochen.spring.session.jpa.entity.security.UserDetailDataEntity;
import com.zhuochen.spring.session.jpa.repository.security.UserDetailDataEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CUserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailDataEntityRepository userDetailDataEntityRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //TODO cache
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetailDataEntity> optional = userDetailDataEntityRepository.findByUsername(username);

        if (optional.isPresent()) {
            UserDetailDataEntity entity = optional.get();
            User user = new User(entity.getUsername(), entity.getPassword(), entity.getAuthorities());
            User.withUsername(entity.getUsername())
                    .password(passwordEncoder.encode(entity.getPassword()));
            BeanUtils.copyProperties(entity, user);
            return user;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
