package com.beststar.book.springboot.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


// 책에서는 JUnit4의 @RunWith(SpringRunner.class)를 사용중이나
// 여기서는 JUnit5에서 @ExtendWith(SpringExtension.class)를 사용
// Spring Boot 2.2.x 버전부터는 기본 JUnit 버전이 5
// 책에는 2.1.7.RELEASE 기준이지만
// 나는 현재 (2021-07-18) 시점 기준으로
// spring.io를 참고하여 GA버전 중 가장 최신 버전인 2.5.2를 적용했으므로 Junit5가 기본

// ExtendWith이 RunWith와 비교했을때 갖는 특징
// 1. 메타 애노테이션 지원
// 2. 여러번 중복 사용 가능
// 이 중 1. 특징을 스프링 부트가 적극 활용하면서
// @ExtendWith(SpringExtension.class)를 생략 가능
// ***Test 라는 이름의 애노테이션에서 이미 그 코드를 갖고 있기 때문
// 관련 링크 : https://www.whiteship.me/springboot-no-more-runwith/

// @WebMvcTest
// 여러 스프링 테스트 애노테이션 중, Web(Spring MVC)에 집중할 수 있는 애노테이션
// Spring Security도 자동으로 테스트하며 수동으로 추가/삭제 가능
// @SpringBootTest 어노테이션은 통합테스트인데,
// @WebMvcTest는 Web 레이어 관련 빈들만 등록하므로 비교적 가볍게 테스트 가능
// 다음과 같은 내용만 스캔하도록 제한
// @Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, HandlerInterceptor
// 그러므로 @Service, @Component, @Repository 등은 사용 불가
// 여기서는 컨트롤러만 사용하기 때문에 선언
// - 관련 링크
// @WebMvcTest : https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/web/servlet/WebMvcTest.html
// Test Auto Configuration의 Test Slices : https://docs.spring.io/spring-boot/docs/current/reference/html/test-auto-configuration.html#test-auto-configuration
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // @Autowired
    // 스프링이 관리하는 빈(Bean)을 주입 받음
    // 동작 원리까지 분석 정리한 글 : https://beststar-1.tistory.com/40

    // MockMvc
    // 웹 API를 테스트할 때 사용
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API를 테스트 할 수 있음
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // mvc.perform(get("/hello"))
        // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
        // 체이닝이 지원되며 아래와 같이 여러 검증 기능을 이어서 선언할 수 있음

        // .andExpect(status().isOk())
        // mvc.perform의 결과를 검증
        // HTTP Header의 Status를 검증
        // 우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증
        // 여기선 OK 즉, 200인지 아닌지를 검증

        // .andExpect(content().string(hello))
        // mvc.perform의 결과를 검증
        // 응답 본문의 내용을 검증
        // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").
                param("name", name).
                param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

        // param()
        // API 테스트할 때 사용될 요청 파라미터를 설정
        // 단, 값은 String만 허용
        // 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능

        // jsonPath()
        // JSON 응답값을 필드별로 검증할 수 있는 메서드
        // $를 기준으로 필드명을 명시
        // 여기서는 name과 amount를 검증하니 $.name, $.amount로 검증

    }

}
