package com.beststar.book.springboot.config;

import com.beststar.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// WebMvcConfigurer
// LoginUserArgumentResolver가 스프링에서 인식될 수 있게 해주는 인터페이스
// 참고 링크
// https://goodgid.github.io/Spring-WebMvcConfigurer/


@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    // HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야함
    // 다른 HandlerMethodArgumentResolver가 필요하다면 같은 방식으로 추가해주면 됨
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }

}


