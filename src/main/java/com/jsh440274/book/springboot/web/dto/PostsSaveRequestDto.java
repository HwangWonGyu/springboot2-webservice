package com.jsh440274.book.springboot.web.dto;

import com.jsh440274.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Entity와 매우 흡사한 형태이지만 Dto클래스를 새로 만드는 이유 -> Entity클래스는 데이터베이스와 맞닿은 핵심 클래스이기 때문에
//Entity클래스를 기준으로 테이블이 생성되고 스키마가 변경된다. --> 중요한 변경 내용.
//화면을 변경하는 사소한 기능에 Entity클래스를 사용하게 되면 수많은 Entity클래스를 사용하는 서비스 클래스나 비즈니스 로직이 꼬일 수 있다.
//자주 변경이 필요한 View를 위한 클래스인 Request와 Response용 Dto는 따로 만들어주는것이 좋다. => DB Layer와 View Layer의 분리
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
