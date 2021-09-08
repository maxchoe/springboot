package com.koscom.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//Auditing : createAt / updateAt / createUser / UpdateUser를 자동으로 관리해주는 기능

@Getter
@MappedSuperclass // (1)
@EntityListeners(AuditingEntityListener.class) // (2)
public abstract class BaseTimeEntity {

    @CreatedDate // (3)
    private LocalDateTime createdDate;

    @LastModifiedDate // (4)
    private LocalDateTime modifiedDate;

}