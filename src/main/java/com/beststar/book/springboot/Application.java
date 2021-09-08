package com.beststar.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableJpaAuditing // JPA Auditing 활성화
// -> @WebMvcTest에서도 스캔하는데, 이 때문에 @Entity 클래스가 테스트간 필요하게 되고 아래와 같은 에러가 발생함
// java.lang.IllegalArgumentException : At least one JPA metamodel must be present!

/*
* @SpringBootApplication
* 이 애노테이션 덕분에 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성이 모두 자동으로 설정됨
* 그리고 이 애노테이션이 있는 위치부터 설정을 읽어가기 때문에
* 이 클래스는 항상 프로젝트 최상단에 위치해야만 함
* */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        /*
        * SpringApplication.run 으로 인해 내장 WAS를 실행
        *
        * 내장 WAS : 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것
        *
        * 이렇게 되면 항상 서버에 톰캣을 설치할 필요가 없게 되고,
        * 스프링 부트로 만들어진 Jar 파일(실행 가능한 Java 패키징 파일)로 실행하면 됨
        *
        * 꼭 스프링 부트에서만 내장 WAS를 사용할 수 있는 것은 아니지만,
        * 스프링 부트에서는 내장 WAS를 사용하는 것을 권장함
        * 그 이유는 '언제 어디서나 같은 환경에서 스프링 부트를 배포'할 수 있기 때문
        * 외장 WAS를 쓴다고 하면 모든 서버는 WAS의 종류와 버전, 설정을 일치시켜야만 함
        * 새로운 서버가 추가되면 모든 서버가 같은 WAS 환경을 구축해야만 함
        * */
    }

}
