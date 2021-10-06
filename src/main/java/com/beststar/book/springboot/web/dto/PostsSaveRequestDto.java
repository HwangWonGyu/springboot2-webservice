package com.beststar.book.springboot.web.dto;

import com.beststar.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

// Entity 클래스를 Request/Response 클래스로 사용해서는 안됨
// Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이며
// 이를 기준으로 테이블이 생성되고 스키마가 변경됨
// 수많은 서비스 클래스나 비즈니스 로직들이 Entity 클래스를 기준으로 동작
// Entity 클래스가 변경되면 여러 클래스에 영향을 끼치지만,
// Request/Response용 Dto는 View를 위한 클래스라 정말 자주 변경이 필요
// View Layer와 DB Layer의 역할 분리를 철저하게 하는 게 좋음
// 실제로 Controller에서 결과값으로 여러 테이블을 조인해서 줘야 할 경우가 빈번하므로
// Entity 클래스만으로 표현하기가 어려운 경우가 많음
// 꼭 Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용해야 함
//
