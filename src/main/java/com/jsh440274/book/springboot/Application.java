package com.jsh440274.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//앞으로 만들 프로젝트의 메인 클래스가 된다는 뜻.
//스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정.
//@SpringBootApplication이 있는 위치부터 설정을 읽어나가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야한다.

//@EnableJpaAuditing이 @SpringBootApplication과 함께 있기 때문에 @WebMvcTest에서도 스캔하게 된다.
//@EnableJpaAuditing을 사용하기 위해서 @Entity가 필요하기 때문에 이를 테스트에서는 사용하기 어렵다.
//별도의 JpaConfig파일을 만들어 사용한다.
//@EnableJpaAuditing//JPA Auditing활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        //run을 통해서 내장 WAS를 실행시킨다.
        //내장 WAS의 장점 : 어디서든 같은 환경으로 배포가 가능
        SpringApplication.run(Application.class, args);
    }
}
