package com.lsj8367.book.springboot.service.posts;

import com.lsj8367.book.springboot.domain.posts.Posts;
import com.lsj8367.book.springboot.domain.posts.PostsRepository;
import com.lsj8367.book.springboot.web.dto.PostsListResponseDto;
import com.lsj8367.book.springboot.web.dto.PostsResponseDto;
import com.lsj8367.book.springboot.web.dto.PostsSaveRequestDto;
import com.lsj8367.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    //readOnly = true 옵션을 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨
    //조회속도가 개선된다. 그렇기 때문에 등록, 수정, 삭제 기능이 없는 서비스 메소드에서 사용하는 것을 권장함.
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postsRepository.delete(posts); //JPARepository 기본 제공 delete 메소드
        //엔티티를 파라미터로 하여 삭제할 수도 있고 deleteById로 특정 id로도 삭제할 수 있다.
        //존재하는 Posts인지 확인을 하고 있으면 조회 후 그대로 삭제한다.
    }

}
