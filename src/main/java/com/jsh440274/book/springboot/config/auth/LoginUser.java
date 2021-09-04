package com.jsh440274.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어노테이션이 생성될 수 있는 위치 지정. element의 타입이 파라미터로 선언된 객체에서만 사용가능하다. 클래스 선언문에 TYPE으로 사용할수도 있음
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//@interface로 LonginUser라는 어노테이션읆 만든다는 의미
public @interface LoginUser {
}
