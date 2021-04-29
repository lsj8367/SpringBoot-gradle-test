package com.lsj8367.book.springboot.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        //assertThat : assertJ라는 테스트 검증 라이브러리의 검증 메소드
        // 검증하고 싶은 대상을 메소드 인자로 받는다.
        // 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있다.
        // isEqualTo 같은가를 비교하는 메소드
        // 같을때만 성공함.
        assertThat(dto.getName()).isEqualTo("test");
        assertThat(dto.getAmount()).isEqualTo(1000);
    }
}
