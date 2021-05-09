package com.lsj8367.book.springboot.service.posts;

import com.lsj8367.book.springboot.posts.PostsRepository;
import com.lsj8367.book.springboot.web.dto.PostsSaveRequestDto;
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
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
