package com.jsh440274.book.springboot.service.posts;

import com.jsh440274.book.springboot.domain.posts.Posts;
import com.jsh440274.book.springboot.domain.posts.PostsRepository;
import com.jsh440274.book.springboot.web.dto.PostsListResponseDto;
import com.jsh440274.book.springboot.web.dto.PostsResponseDto;
import com.jsh440274.book.springboot.web.dto.PostsSaveRequestDto;
import com.jsh440274.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    //update의 경우 쿼리를 날리는 부분이 없다. -> JPA의 영속성 컨텍스트 때문(엔티티를 영구적으로 저장하는 환경)
    //트랜젝션 안에서 데이터베이스의 데이터를 가져오면 영속성이 유지된 상태.
    //트랜젝션이 끝나는 시점에 해당 테이블에 변경분을 반영한다. -> update쿼리를 날릴 필요 없이 Entity객체의 값만을 변경하면 됨.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postsRepository.delete(posts);
    }
}
