package com.example.springproject.domain.user;

import com.example.springproject.domain.BaseTimeEntity;
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

    public User updateNameAndPicture(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }
    // 객체의 행위 내부 로직에 대해 외부에서 알 수 없어야 한다. - 객체지향의 사실과 오해
    // 여기 정의된 public 메소드 전부 private으로 해줘야 하는 것 아닌가? 책에서는 왜 public으로 되어 있을까??
    // 그리고 만약 private로 바꾼다면, updateNameAndPicture에서 map을 사용하고 있는데, 이건 어떻게 처리하는게 좋을까?

    public String getRoleKey() {
        return this.role.getKey();
    }
}
