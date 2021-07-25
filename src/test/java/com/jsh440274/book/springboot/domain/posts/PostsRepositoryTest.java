package com.jsh440274.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //Junit에서 단위테스트가 끝날때마다 수행하는 메소드.
    //배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막을 수 있다.
    //여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아있어 다음 테스트 실행 시 테스트가 실패할 수 있음.
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //테이블 posts에 insert/update를 수행한다.
        //이미 pk값(id)가 존재한다면 update, 아니면 insert를 실행.
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jsh440274@cnu.ac.kr")
                .build());

        //when
        //테이블 posts에서 모든 데이터를 조회한다.
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
