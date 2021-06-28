package com.lsj8367.book.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsj8367.book.springboot.domain.posts.Posts;
import com.lsj8367.book.springboot.domain.posts.PostsRepository;
import com.lsj8367.book.springboot.web.dto.PostsSaveRequestDto;
import com.lsj8367.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // JPA까지 한번에 테스트할때 사용
//WebEnvironment.RANDOM_PORT 로 인한 랜덤 포트실행
//@WebMvcTest의 경우 JPA기능이 작동하지 않기 때문에 사용하지 않았다.
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; //JPA까지 한번에 테스트할때 사용함

    @Autowired
    private PostsRepository postsRepository;

    private MockMvc mvc;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Autowired
    private WebApplicationContext context;

    @Before
    // 테스트 시작전에 MockMvc 인스턴스 생성
    // Junit5에서 @BeforeEach와 같은 어노테이션
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                             .apply(springSecurity())
                             .build();

    }

    @Test
    @WithMockUser(roles = "USER")
    // 인증된 모의(가짜) 사용자를 만들어서 사용
    // roles에 권한 추가 가능
    // 이 어노테이션으로 인해 ROLE_USER 권한을 가진 사용자가 API를 요청하는것과 동일한 효과
    public void Posts_등록된다() throws Exception{
        //given
        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                                            .title(title)
                                                            .content(content)
                                                            .author("author")
                                                            .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        //mvc.perform => 생성된 MockMvc를 통해 API테스트
        // 본문영역은 문자열로 표현하기 위해 ObjectMapper를 통해 문자열 JSON 변환
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                             .content(new ObjectMapper().writeValueAsString(requestDto)))
                             .andExpect(status().isOk());

        //then
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Posts_수정된다() throws Exception{
        //given
        Posts savePosts = postsRepository.save(Posts.builder()
                                                    .title("title")
                                                    .content("content")
                                                    .author("author")
                                                    .build());


        Long updateId = savePosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                                                                .title(expectedTitle)
                                                                .content(expectedContent)
                                                                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        //when
        mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(new ObjectMapper().writeValueAsString(requestDto)))
                            .andExpect(status().isOk());

        //then
        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
