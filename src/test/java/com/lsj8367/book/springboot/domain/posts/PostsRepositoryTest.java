package com.lsj8367.book.springboot.domain.posts;

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

    @Autowired
    private PostsRepository postsRepository;

    @After
    // Junit에서 단위테스트가 끝날때마다 수행되는 메소드
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    public void insertDataTest(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // 테이블 posts에 insert/update 쿼리 실행
        //id값이 있으면 update, 없으면 insert 쿼리 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("lsj8367@naver.com")
                .build());

        // when
        // findAll은 모든 데이터를 조회
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2021, 5, 25, 0, 0, 0);
        postsRepository.save(Posts.builder()
                                  .title("title")
                                  .content("content")
                                  .author("author")
                                  .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate = " + posts.getCreatedDate() + ", modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
