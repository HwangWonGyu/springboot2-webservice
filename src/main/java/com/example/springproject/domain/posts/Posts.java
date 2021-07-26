package com.example.springproject.domain.posts;

//domain 패키지가 어떤 뜻을 의미할까?
//책에서도 domain이라는 의미를 정확히 이해하려면 최범균님의 다른 책을 읽어보라고 할 정도다.
//책에서는 도메인이란 게시글, 댓글, 회원, 정산, 결제 등 소프트웨어에 대한 요구사항 혹은 문제 영역이라고 생각하면 된다던데
// Service or Controller와는 전혀 다른 개념인가??
// 내가 생각하는 Service는 비즈니스 로직을 처리하는 계층이고, Controller는 클라의 Request를 받아 라우팅해주는 계층이다.

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
// 기본 생성자를 자동으로 추가해주는 것이다. JPA에서는 기본 생성자를 무조건 가지고 있어야 한다고 한다. https://ttungbab.tistory.com/198
//  JPA 같은걸 구현해서 쓰는 라이브러리들이 동적으로 뭘 한다거나 리플렉션을 한다거나 이런 것들이 있는데, 그런 것들을 하려면 기본 생성자가 필요하기 떄문이다.
@Entity
// 객체를 엔티티로 매핑시켜주는 어노테이션 https://ttungbab.tistory.com/198
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // IDENTITY속성은 기본키 생성을 디비에 위임하는 것이다. https://ttungbab.tistory.com/200 - 여기에 시퀀스와 INDENTITY의 차이 등 모든 속성에 대한 설명도 추가했다.
    // 각 디비에 따라 알아서 기본키 전략을 선택하고, 디비에 위임하기 때문에 값에는 null을 넣어줘야 한다.

    @Column(length = 500, nullable = false)
    private String title;
    // 이러한 필드 컬럼에 대한 속성은 디비를 생성할 때, 이 속성에 따라 맞춰 생성해준다. https://ttungbab.tistory.com/199
    // 이 말은 즉, 생성 이후에는 딱히 쓸모가 없다는 의미로 받아 들여졌다.
    // 그럼 굳이 사용하는 이유는 단순히 타입을 명시하기 위함인가??

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

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



    // @Builder를 사용하는 이유는 무엇일까? 그냥 생성자로 생성하는 것이랑 무슨 차이가 있을까??

    // 그 이유는 생성자의 단점이 있기 때문이다.
    // 생성자에는 선택적 매개변수가 많은 경우 대응이 어렵다는 문제점이 있다.
    // 그리고 코드를 읽을 때 각 값의 의미가 무엇인지 헷갈린다.
    // 하지만 Builder패턴은 빌더에 넘기는 매개변수에 따라 다른 객체를 만들 수 있다.
    // 하지만 비용이 크다는 단점과 코드가 장황해지기 때문에 매개변수가 4개 이상은 되어야 값어치를 한다고 한다.
    // https://devfunny.tistory.com/337
}


// 갑자기 궁금해 진 것인데, 스프링에서 의존성 주입 할 때, 생성자 주입, Setter주입, 필드 주입이 있었다.
// 그럼 위에 클래스는 무슨 주입이 되는거지??
// 지금 내 생각에는 저 객체는 다른 객체에 의존하고 있는게 없기 때문에, 주입될 것이 없는 것 같다.
// 근데 만약 있다면 가정한다면,, @NoArgsConstructor로 기본 생성자가 생성될 것이고, @Builder로 주입이 되는건가??
// 책에서는 @Builder가 생성자를 대신 하는데, Builder가 생성자 생성 시점에 값을 채워주는 역할은 똑같다고 한다.
