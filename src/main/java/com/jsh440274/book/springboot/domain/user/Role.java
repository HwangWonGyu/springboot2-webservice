package com.jsh440274.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//Enum클래스에 대해서 알아보기
//key-value값을 묶어주는 클래스 -> 기존의 맵과 무엇이 다른가?
@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
