package com.jsh440274.book.springboot.domain.user;

import com.jsh440274.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    //왜 그냥 long이 아니라 Long일까?
    /*
    id의 타입이 long일 경우 0으로 초기화되어 실제 0의 값인지 초기화된 값인지를 구분하기가 힘들어진다.
    Long의 wrapper타입으로 선언해주면 Nullable하게 명시할 수 있어 0을 구분하기 용이하다.
    https://hch4102.tistory.com/106
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    //보통은 디폴트값으로 ORDINAL이 들어간다. 그렇게 되면 순서대로 0번부터 숫자가 배정되고, 새로운 분류를 맨뒤 숫자의 키값으로 지정하고 싶지 않을 때 문제가 된다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
