package com.zhuochen.spring.session.jpa.entity.security;

import com.zhuochen.spring.session.jpa.CreateAndModifyListener;
import com.zhuochen.spring.session.jpa.entity.base.BaseDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_role_data")
@EntityListeners(CreateAndModifyListener.class)
public class UserRoleDataEntity extends BaseDataEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_data_id")
    private Long userRoleDataId;

    @Column(name = "authority")
    private String authority;

    @Column(name = "user_detail_data_id")
    private Long userDetailDataId;

    @ManyToOne
    @JoinColumn(name = "user_detail_data_id", referencedColumnName = "user_detail_data_id", insertable = false, updatable = false)
    private UserDetailDataEntity userDetailDataEntity;
}
