package com.zhuochen.spring.session;

import com.zhuochen.spring.session.jpa.entity.security.UserDetailDataEntity;
import com.zhuochen.spring.session.jpa.entity.security.UserRoleDataEntity;
import com.zhuochen.spring.session.jpa.repository.security.UserDetailDataEntityRepository;
import com.zhuochen.spring.session.jpa.repository.security.UserRoleDataEntityRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class SessionApplicationTests {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailDataEntityRepository detailDataEntityRepository;

    @Autowired
    private UserRoleDataEntityRepository roleDataEntityRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void user1() {
        UserDetailDataEntity dataEntity = new UserDetailDataEntity();
        dataEntity.setUsername("u1");
        dataEntity.setPassword(passwordEncoder.encode("p1"));

        detailDataEntityRepository.save(dataEntity);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void role1() {
        UserRoleDataEntity userRoleDataEntity = new UserRoleDataEntity();
        userRoleDataEntity.setAuthority("ROLE_ADMIN1");
        userRoleDataEntity.setUserDetailDataId(1L);

        roleDataEntityRepository.save(userRoleDataEntity);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void role2() {
        UserRoleDataEntity userRoleDataEntity = new UserRoleDataEntity();
        userRoleDataEntity.setAuthority("ROLE_ADMIN2");
        userRoleDataEntity.setUserDetailDataId(1L);

        roleDataEntityRepository.save(userRoleDataEntity);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void user2() {
        UserDetailDataEntity dataEntity = new UserDetailDataEntity();
        dataEntity.setUsername("u2");
        dataEntity.setPassword(passwordEncoder.encode("p2"));

        detailDataEntityRepository.save(dataEntity);
    }

    @Test
    public void decode() {
//        String js = CommonStaticService.decrypt("8gNDaBV5N24/e6n61tYkM2KOI8sAxNYPUVmmt5a0JudXo0Hb9EUp7DGjS1LaHsK2nuCBHkvgKTbYgn9zxg1PPM1rs5/6M3bBgKsDu7//BhgtdVFJRxf/qc+w22Tx08Y5/iEhDAeGhr6dQ3Fc/NCtbi9pGNvZELKPp3YP8ItIigM=\n");
//        WrappedSession session = CommonStaticService.getObjectFromJson(js, WrappedSession.class);
    }

}
