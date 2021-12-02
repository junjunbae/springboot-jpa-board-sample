package com.example.sampleboard.entity.base;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * 테이블에 기본적으로 포함되는 생성자, 생성일시, 수정자, 수정일시 컬럼
     * = JpaAuditConfig.java (설정)
     */

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createId;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedBy
    private String modifiedId;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
