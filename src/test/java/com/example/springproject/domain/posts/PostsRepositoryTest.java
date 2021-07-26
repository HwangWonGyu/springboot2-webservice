package com.example.springproject.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    // 이건 왜 사용하는거지?
    // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드이다.
    // 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용한다.
    // 여러 테스트가 동시에 수행되면 테스트용 디비인 H2에 데이터가 그대로 남아 있어 다음 테스트 실행 시 테스트가 실패할 수 있기 때문이다.
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("cndqja@gmail.com")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

        // 왜 게시글을 저장하는 것만 메소드에 담은 것이 아니라, 저장하고 불러오는거까지를 테스트 단위로 잡았는지 궁금하다.
        // 저장하는 부분과 불러오는 부분 것 각각의 단위 테스트를 해야 하는게 맞지 않나??
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        // 왜 findById로 찾지 않고, 전부 다 찾은 다음에 get(0)을 할까??
        // save 때 어차피 디비에서 Id를 가져오니, findById로 찾는게 훨씬 효율적이지 않을까??

        System.out.println(">>> createDate="+posts.getCreateDate()+", modefiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
    // 여기서 isAfter은 어떤 함수일까? 생성 때의 now와 같은지 확인하는 것 같긴 한데,,,
    // 이렇게 어떤 함수인지, 어떤 역할을 하고 어떤 구조로 되어 있는지 알고 싶을 때 다른 사람들은 어떻게 접근하는지 알아보자.
}

// 이거 실행시키면, 데이터를 집어 넣어도, 마지막에 전부 delete해버린 후에 테이블까지 드랍한다.
// 근데 테이블 drop은 나가는데, h2에 posts테이블이 남아있긴 하네,,? 뭐지
