package com.cndqja.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing이란 https://hyem-study.tistory.com/115
@EnableJpaAuditing
@SpringBootApplication
public class SpringBootWebService {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebService.class, args);
    }
}
