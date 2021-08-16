package com.beststar.book.springboot.domain.user;

import com.beststar.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    // @Enumerated(EnumType.STRING)
    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정함
    // 기본적으로는 int로 된 숫자가 저장됨
    // 숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가 없음
    // 그래서 문자열 (EnumType.STRING)로 저장될 수 있도록 선언

    // + 추가로 @Enumerated(EnumType.ORDINAL)이 default인데,
    // 이는 enum 순서대로 int로 된 숫자가 DB에 저장돼 데이터 크기가 작다는 장점이 있으나
    // 이미 저장된 enum의 순서를 변경할 수 없다는 단점이 있음
    // 예) GUEST(0), USER(1) 에서 ADMIN(0), GUEST(1) USER(2) 로 변경되면
    // DB에 이미 저장된 데이터들의 의미가 불일치하게됨
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
