package com.beststar.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


// 필자는 주요 어노테이션을 클래스에 가깝게 두었음
// @Entity는 JPA, @Getter와 @NoArgsConstructor는 롬복
// 이후에 코틀린 등의 새 언어 전환으로 롬복이 더이상 필요 없을 경우 쉽게 삭제할 수 있음

// 1. JPA의 애노테이션

// @Entity
// 테이블과 링크될 클래스임을 나타냄
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
// ex) SalesManager.java -> sales_manager.table

// @Id
// 해당 테이블의 PK 필드를 나타냄
// *웬만하면 Entity의 PK는 Long 타입의 Auto_increment를 추천(MySQL 기준으로 이렇게 하면 bigint 타입이 됨)
// 그래서 주민등록번호와 같이 비즈니스상 유니크 키나, 여러 키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 종종 발생
// 1) FK를 맺을 때 다른 테이블에서 복합키 전부를 갖고 있거나, 중간 테이블을 하나 더 둬야 하는 상황이 발생
// 2) 인덱스에 좋은 영향을 끼치지 못함
// 3) 유니크한 조건이 변경될 경우 PK 전체를 수정해야 하는 일이 발생
// 주민등록번호, 복합키 등은 유니크 키로 별도로 추가하는게 좋음


// @GeneratedValue
// PK의 생성 규칙을 나타냄
// 스프링 부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨

// @Column
// 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됨
// 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
// 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용됨

// 2. Lombok의 애노테이션

// @NoArgsConstructor
// 기본 생성자 자동 추가
// public Posts() {}와 같은 효과

// @Getter
// 클래스 내 모든 필드의 Getter 메서드 자동 생성

// @Builder
// 해당 클래스의 빌더 패턴 클래스를 생성
// 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
// - 참고 링크
// 빌드 패턴 사용 이유 : https://mangkyu.tistory.com/163
// 빌드 패턴 정리 : https://johngrib.github.io/wiki/builder-pattern/


// 자바빈 규약을 생각하면서 getter/setter 를 무작성 생성하는 경우가 있음
// 그런데 이렇게 되면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상 명확히 구분하기 힘들어서
// 차후 기능 변경시 복잡해짐
// 그래서 Entity 클래스에서는 Setter 메서드를 만들지 않고,
// 필드 값 변경이 필요할때 목적과 의도가 나타나는 메서드를 만듦
// ex)
// public class Order {
//      public void cancelOrder() {
//          this.status = false;
//      }
// }
//
// public void 주문서비스의_취소이벤트() {
//      order.cancelOrder();
// }
@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
