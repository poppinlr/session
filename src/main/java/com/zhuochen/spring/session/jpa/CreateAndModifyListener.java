package com.zhuochen.spring.session.jpa;


import com.zhuochen.spring.session.jpa.entity.base.BaseDataEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class CreateAndModifyListener {

    @PrePersist
    public void setCreatedAtAndCreatedBy(BaseDataEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
        setModifiedAtAndModifiedBy(entity);
    }

    @PreUpdate
    public void setModifiedAtAndModifiedBy(BaseDataEntity entity) {
        entity.setModifiedAt(LocalDateTime.now());
    }
}
