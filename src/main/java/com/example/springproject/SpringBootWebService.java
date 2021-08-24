package com.example.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing이란 https://hyem-study.tistory.com/115
//  @EnableJpaAuditing WebMvcTest에서도 스캔하게 되서, 따로 분리해줘야 한다.
@SpringBootApplication
public class SpringBootWebService {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebService.class, args);
    }
}
