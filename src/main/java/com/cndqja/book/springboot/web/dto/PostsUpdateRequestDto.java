package com.cndqja.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
// UpdateDto는 굳이 왜 만든 것일까??
// 이런 부분을 실제 프로젝트 하면서 한 적이 있는데, 그때는 따로 dto클래스를 만들지 않고, 파라미터를 여러 개 넘겨줬다.
// 단순 가독성을 위함인가 아니면 view에 반환해 줄 때, 객체에 담아서 보내줘야 하기 때문인가??
