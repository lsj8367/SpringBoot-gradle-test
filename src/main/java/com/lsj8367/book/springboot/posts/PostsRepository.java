package com.lsj8367.book.springboot.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { //JPArepository 상속받으면 기본 CRUD 생성 자동으로 된다.
    //이렇게 하면 @Repository 어노테이션을 지정해줄 필요가 없지만 이 조건이 만족하게 하려면
    // Entity클래스와 Entity Repository가 같이 위치해야한다.
}
