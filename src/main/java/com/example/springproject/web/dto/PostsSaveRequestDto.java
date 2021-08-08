package com.example.springproject.web.dto;

import com.example.springproject.domain.posts.Posts;
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
    public PostsSaveRequestDto(String title, String content, String author){
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

// Dto는 데이터를 계층간에 주고 받을 때, 사용하게 된다.
// Entity를 그대로 주고 받다 보면, 화면에서 요구하는 데이터 형식과 맞지 않을 때가 있고, 이 때문에 수정이 잦아진다.
// 그리고 이러한 이유로 디비 컬럼을 변경하는 것은 너무 큰 변경이다.

// -> 이거 무슨 말인지 이해했는데,, 남에게 설명하라고 한다면 잘못 설명할 것 같다.. 왜일까?