package com.jsh440274.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//Posts클래스로 DB를 접근하게 해줄 JpaRepository.
//보통 MyBatis에서 Dao라고 부르는 DB Layer접근자에 해당한다.
//인터페이스 생성 후 JpaRepository<Entity클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
//@Repository 어노테이션을 추가하지 않아도 알아서 작동함.
//꼭 Entity클래스와 Entity Repository는 같은 위치에 있어야한다.
//두가지중 하나라도 없으면 제대로 동작하지 않으니 주의
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
