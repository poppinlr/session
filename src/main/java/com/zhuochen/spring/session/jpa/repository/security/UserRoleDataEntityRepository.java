package com.zhuochen.spring.session.jpa.repository.security;

import com.zhuochen.spring.session.jpa.entity.security.UserDetailDataEntity;
import com.zhuochen.spring.session.jpa.entity.security.UserRoleDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleDataEntityRepository extends JpaRepository<UserRoleDataEntity, Long> , QuerydslPredicateExecutor<UserRoleDataEntity> {

}
