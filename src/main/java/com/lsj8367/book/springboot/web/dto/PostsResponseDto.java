package com.lsj8367.book.springboot.web.dto;

import com.lsj8367.book.springboot.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    // Entity의 필드 중 일부만 사용
    //모든 값을 가진 생성자가 필요하지 않으므로 Entity만 받아서 처리
    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
