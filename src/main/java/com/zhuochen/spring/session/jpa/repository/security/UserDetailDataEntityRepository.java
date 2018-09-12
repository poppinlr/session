package com.zhuochen.spring.session.jpa.repository.security;

import com.zhuochen.spring.session.jpa.entity.security.UserDetailDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailDataEntityRepository extends JpaRepository<UserDetailDataEntity, Long>, QuerydslPredicateExecutor<UserDetailDataEntity> {

    Optional<UserDetailDataEntity> findByUsername(String username);
}
