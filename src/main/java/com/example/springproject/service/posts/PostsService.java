package com.example.springproject.service.posts;

import com.example.springproject.domain.posts.Posts;
import com.example.springproject.domain.posts.PostsRepository;
import com.example.springproject.web.dto.PostsListResponseDto;
import com.example.springproject.web.dto.PostsResponseDto;
import com.example.springproject.web.dto.PostsSaveRequestDto;
import com.example.springproject.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        // 자바에서 orElseThrow문이 어떻게 구성되어 있는지?
        // Optional에 대한 글 https://mangkyu.tistory.com/70
        // + 자바 예외처리 계층 구조 https://velog.io/@youngerjesus/%EC%9E%90%EB%B0%94-%EC%98%88%EC%99%B8-%EC%B2%98%EB%A6%AC (IllegalArgumentException보고 궁금해졌다.)

        posts.update(requestDto.getTitle(), requestDto.getContent());
        // 이걸 바꾸는게 무슨 의미이지?? 이 과정 배웠으니 설명해보자.
        // 아 JPA의 영속성 컨텍스트에서 해당 엔티티를 불러오면 1차 캐시에 저장되고, 스냅샷을 저장한다.
        // 그리고 객체의 수정이 일어나면 1차 캐시의 해당 엔티티 값이 변하고,
        // flush가 실행될 때, 스냅샷과 1차 캐시의 엔티티 값이 차이가 있으니 update쿼리문을 생성할 것이다.

        // 근데 여기서는 커밋 시점이 언제이지...? @Transactional의 정확한 의미를 찾아보자.

        // 트랜잭션 관련 개념 https://devuna.tistory.com/30
        // Spring에서 @Transactional의 내부 동작 과정 https://jeong-pro.tistory.com/228 - 나도 이렇게 공부하고 싶다.

        // 정리하자면, @Transactional을 통해 커밋을 하고, 예외가 발생하면 롤백을 한다.
        // 이때 커밋 시점에서 스냅샷과 1차 캐시를 비교해서 update쿼리문을 날리는 것이다. - 이걸 더티체킹이라고 한다.
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAll() {
        return postsRepository.findAll().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }


    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}


// 순간 롬복이 그냥 생략이 가능하고 간편해서 가독성을 높여주기 때문에, 사용하는 줄 알았다.
// 책에서 나와 있듯이, @RequiredArgsConstructor를 통해 의존성이 변경되어도 생성자 코드를 수정하는 일이 없어지게 된다.