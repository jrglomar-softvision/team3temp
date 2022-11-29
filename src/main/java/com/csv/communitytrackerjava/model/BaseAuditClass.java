package com.csv.communitytrackerjava.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseAuditClass {

    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

}