package com.jsh440274.book.springboot.web.dto;

import com.jsh440274.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    //Entity 필드의 일부만 사용하기 때문에 생성자로 Entity를 받는다.(모든 필드를 가진 생성자가 필요하지 않다.)
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
