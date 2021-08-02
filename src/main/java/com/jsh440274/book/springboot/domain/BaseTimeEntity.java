package com.jsh440274.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//JPA Auditing에 관한 내용.
//LocalDateTime을 이용해서 데이터베이스에 날짜 로그를 남길 수 있다.
//(기존에 사용하던 Date와 Calendar는 변경이 불가능한 불변객체가 아니기 때문에 멀티스레드 환경에서 문제가 발생할 수 있고
// Calendar은 1월이 0부터 시작한다는 단점이 있었다.)
//BaseTimeEntity는 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하게 된다.
@Getter
//JPA Entity클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 한다.
@MappedSuperclass
//BaseTimeEntity클래스에 Audiㅅing기능을 포함시킨다.
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    //Entity가 생성되어 저장될 때 시간이 자동으로 저장됨.
    @CreatedDate
    private LocalDateTime createdDate;

    ////조회한 Entity의 값을 변경할 때 시간이 자동 저장됨.
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
