package com.jsh440274.book.springboot.web;

import com.jsh440274.book.springboot.config.auth.SecurityConfig;
import com.jsh440274.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.(여기서는 SpringRunner)
//스프링 부트 테스트와 JUnit 사이의 연결자 역할

//여러 테스트 어노테이션 중 웹(Spring MVC)에 집중할 수 있는 어노테이션
//사용할 경우 @Controller, @ControllerAdvice 사용 가능, @Service, @Component, @Repository등 사용 불가능.
@RunWith(SpringRunner.class)
//@Service, @Component, @Repository는 스캔대상이 아니기 때문에
//SecurityConfig를 스캔하지만 이를 생성하는데 필요한 CustomOAuth2UserService는 읽지 않는다.
//여기서는 SecurityConfig를 사용할 필요가 없기 때문에 제외해줌.
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })
public class HelloControllerTest {

    //스프링이 관리하는 빈 자동 주입
    //웹 api를 테스트할 때 사용.
    //스프링 MVC 테스트의 시작점.
    //이 클래스를 통해 HTTP GET, POST등에 대한 API 테스트 가능.
    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        //MockMvc를 통해 /hello주소로 HTTP GET 요청
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
        //mvc.perform의 결과 검증
        //HTTP Header의 Status를 검증한다.
        //200, 404, 500 등의 숫자로 나타나는 Status검증
        //isOK이므로 200인 경우를 뜻한다.
        //mvc.perform의 결과 검증
        //응답된 본문의 내용을 검증한다.
        //Controller에서 "hello" 값을 리턴하는지 검증한다.
    }
}
