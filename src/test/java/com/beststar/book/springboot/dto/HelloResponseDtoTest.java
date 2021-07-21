package com.beststar.book.springboot.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

        // assertThat()
        // assertj라는 테스트 검증 라이브러리의 검증 메서드
        // 검증하고 싶은 대상을 메서드 인자로 받음
        // 메서드 체이닝이 지원돼 isEqualTo와 같이 메서드를 이어서 사용 가능

        // isEqualsTo()
        // assertj의 동등 비교 메서드
        // assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공


        // - assertj vs JUnit

        // 1) CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음
        // JUnit의 assertThat을 쓰게 되면 is()와 같이 CoreMatchers 라이브러리가 필요
        // 보통 메서드를 외우고 사용하는게 아니라 자동완성으로 찾아서 사용하는데,
        // CoreMatchers는 안되고 assertj는 됨
        // 관련 내용 : https://bit.ly/30vm9Lg

        // * CoreMatchers
        // Hamcrest라는 테스트 프레임워크의 Matcher 클래스
        // * Matcher
        // 필터나 검색등을 위해 값을 비교할 때 좀 더 편리하게 사용하도록 도와주는 것

        // 2) 자동완성이 좀 더 확실하게 지원됨
    }

}
