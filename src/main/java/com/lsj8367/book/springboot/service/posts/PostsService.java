package com.lsj8367.book.springboot.service.posts;

import com.lsj8367.book.springboot.domain.posts.Posts;
import com.lsj8367.book.springboot.domain.posts.PostsRepository;
import com.lsj8367.book.springboot.web.dto.PostsResponseDto;
import com.lsj8367.book.springboot.web.dto.PostsSaveRequestDto;
import com.lsj8367.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    // @Autowired는 순환 참조 문제를 야기함.
    // 객체 생성 시점에 빈을 주입하기 때문에 서로 참조하는 객체가 생성되지 않은 상태에서
    // 그 빈을 참조하기 때문에 오류가 발생한다.
    // 그래서 생성자로 값을 주입하는데 여기서 생성자는 RequiredArgsConstructor가 담당한다.
    // 롬복의 requiredConstructor는 final이 선언된 모든 필드를 인자값으로 하는 생성자를 만들어준다.

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) { //등록
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){ //수정
        Posts posts = postsRepository.findById(id)
                                     .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        //IllegalArgumentException ==> 쿼리가 에러났을때 = id값에 해당하는 데이터가 없을때

        posts.update(requestDto.getTitle(), requestDto.getContent());

        // 쿼리를 날리는 부분이 없다.
        // JPA의 영속성 컨텍스트 때문.
        // 영속성 컨텍스트란, Entity를 영구 저장하는 환경이다. 논리적 개념으로 보면되고
        // JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 나뉜다.
        return id;
    }

    public PostsResponseDto findById(Long id){ //조회
        Posts entity = postsRepository.findById(id)
                                      .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }
}
