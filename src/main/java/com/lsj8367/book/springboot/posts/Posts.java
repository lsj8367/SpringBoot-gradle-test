package com.lsj8367.book.springboot.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // 실제 DB테이블과 매칭되는 클래스
// 기본값으로 클래스의 camelCase 이름을 스네이크케이스 테이블과 매칭시켜준다.
public class Posts {
    @Id // 해당 테이블의 PK임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity 옵션을 넣어야 auto_increment 설정 가능
    private Long id;

    @Column(length = 500, nullable = false) // 해당클래스는 변수만 선언해도 column이 되지만
    // 추가로 변경이 필요한 옵션이 있을때 사용한다. ex) varchar max_length 기본값은 255인데, 500으로 늘리고 싶을때
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    //여기처럼 타입을 TEXT로 변경하거나, null값을 못넣게 설정할 수 있다.
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }


}
