package com.jsh440274.book.springboot.config.auth.dto;

import com.jsh440274.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

//인증된 사용자 정보만 필요하기 때문에 name, email, picture만 필드에 있다.
//세션에 User클래스를 저장하려고 하면 직렬화가 안되어 있어 불가능하게 됨.
//그렇다고 직렬화해주는 코드를 User클래스에 넣게 되면 엔티티인 User클래스와 연관된 모든 엔티티들이 직렬화 대상이 되어버림
//확장성이 구려질 수 있기 때문에 직렬화 기능을 가진 세션 Dto를 새로 만드는 것이 좋다.
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
