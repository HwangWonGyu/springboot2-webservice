package com.example.springproject.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}

// @MappedSuperClass는 뭘까?
// -> 테이블로 생성되지 않고, 이것을 상속받는 엔티티의 컬럼에 추가된다. LocalDateTime과 같이 많은 테이블에서 공통적으로 사용되는 컬럼을 추상화 시킨 느낌이다.
// @EntityListeners는 뭘까
// BaseTimeEntity 클래스에 Auditing기능을 포함시킨다고 하는데, 아직 이해하지 못했다.


