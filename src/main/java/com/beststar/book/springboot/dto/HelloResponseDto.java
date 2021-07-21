package com.beststar.book.springboot.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// @Getter
// 선언한 모든 필드의 get 메서드를 생성

// @RequiredArgsConstructor
// 선언된 모든 final 필드가 포함된 생성자를 생성
// final이 없는 필드는 생성자에 포함 안됨

@Setter
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
