//도메인이란 소프트웨어에 대한 요구사항, 문제영역이다.
package com.jsh440274.book.springboot.domain.posts;

import com.jsh440274.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//주요 어노테이션이 클래스와 가깝게(롬복 관련 보다 JPA 관련 어노테이션이 가깝게) 배치. -> 불필요해진 경우(다른 언어로 전환하는 등) 삭제가 용이하다.
@Getter
//기본 생성자 자동 추가.
/*
기본생성자(public class Posts(){})는 꼭 명시되어 있어야 하는가?
    -> JPA의 구현체들은 기본 생성자가 있어야 이를 기반으로 객체를 프록시 하거나 내부에서 생성하여 사용할 수 있기 때문에 필요하다.
    JPA의 프록시 관련 내용 참고 링크 https://victorydntmd.tistory.com/210
*/
@NoArgsConstructor
//테이블과 링크 될 클래스임을 나타낸다.
//JPA를 사용한다는 뜻은 DB데이터에 실제 쿼리를 날리는 것이 아니라 이 클래스를 수정해가면서 사용한다는 뜻이다.
//기본값으로 클래스의 카멜케이스 이름(각 단어의 첫번째 글자를 대문자로 표시)을 언더스코어 네이밍(모두 소문자 표기 후 단어 사이에 _를 삽입)으로 테이블 이름을 매칭
//ex) SalesManager.java -> sales_manager 테이블
@Entity
//BaseTimeEntity를 상속받아 JPA Auditing이 가능하게 함.
public class Posts extends BaseTimeEntity {
    //해당 테이블의 PK
    @Id
    //PK생성규칙. GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //테이블의 칼럼을 의미. 굳이 명시하지 않아도 해당 클래스의 필드는 모두 칼럼으로 취급된다.
    //기본값 이외에 필요한 옵션이 있을 경우 사용.
    //문자열의 경우 VARCHAR(255)가 기본값이므로 아래 예시는 이 길이를 바꾸기 위해서 사용.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //해당 클래스의 빌더 패턴 클래스를 생성한다.
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함한다.
    //빌더 패턴이란?
    //@Setter가 없는 상황에서 DB에 삽입을 해야 하는 경우 보통 생성자를 통해 최종 값을 채운 후 DB에 삽입하게 되는데 생성자를 만드는 대신 빌더를 사용할 수 있다.
    //생성자와 달리 지금 채워야 할 필드가 무엇인지를 명확히 지정할 수 있는 장점이 있다.
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
