package com.beststar.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // findByEmail
    // 소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메서드

    // Spring Data Jpa에서 쿼리 메서드 안에 지원되는 키워드를 사용한 것. 즉, 메서드 이름으로 쿼리를 생성한 것
    // 인터페이스에 정의한 findByEmail() 메서드를 실행하면
    // 스프링 데이터 JPA는 메소드 이름을 분석하여 JPQL을 생성하고 실행
    // 생성된 JPQL은 아래와 같음
    // select m from User m where m.email = ?1
    //
    // 메서드 이름은 관례에 따라 정의해야함
    // Spring Data Jpa 공식 문서를 참고하면 메서드 이름을 어떻게 정의해야 하는지 이해할 수 있음
    // 쿼리 메서드 관례는 아래 링크 참고
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.return.types
    Optional<User> findByEmail(String email);

}
