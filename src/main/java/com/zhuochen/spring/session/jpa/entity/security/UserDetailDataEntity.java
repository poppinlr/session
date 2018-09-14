package com.zhuochen.spring.session.jpa.entity.security;

import com.zhuochen.spring.session.jpa.CreateAndModifyListener;
import com.zhuochen.spring.session.jpa.entity.base.BaseDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_detail_data")
@EntityListeners(CreateAndModifyListener.class)
@ToString(exclude = "authorities")
public class UserDetailDataEntity extends BaseDataEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_detail_data_id")
    private Long userDetailDataId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @OneToMany(mappedBy = "userDetailDataEntity")
    private List<UserRoleDataEntity> authorities;

    @Override
    public boolean isEnabled() {
        return Optional.of(this.getActive())
                .orElse(false);
    }
}
