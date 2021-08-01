package com.beststar.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// @MappedSuperclass
// JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 컬럼으로 인식하도록 함

// @EntityListeners(AuditingEntityListener.class)
// BaseTimeEntity 클래스에 Auditing 기능을 포함시킴

// 보통 엔티티에는 데이터의 생성, 수정 시간을 포함하게 되는데
// 매번 DB에 삽입하기 전, 갱신하기 전에 날짜 데이터를 등록, 수정하는 코드가 여러번 들어가게 됨
// 반복적인 코드를 줄이기 위해 JPA Auditing을 활용

// @CreatedDate
// Entity가 생성돼 저장될 때 시간이 자동 저장됨

// @LastModifiedDate
// 조회한 Entity의 값을 변경할 때 시간이 자동 저장됨


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
