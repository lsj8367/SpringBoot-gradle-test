package com.lsj8367.book.springboot.web;

import com.lsj8367.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
// 여기서는 SpringRunner라는 스프링 실행자를 사용한다.
// 즉 스프링부트 테스트와 Junit사이의 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class,
            // @Component가 스캔대상에서 제외되는 @WebMvcTest기 때문에 excludeFilter에서 Security를 스캔대상에서 제거해준다.
            // 왜? CustomOAuth2UserService를 읽을수 없는 에러를 냈기 때문.
            excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
            })
//여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller, @ControllerAdvice 등은 사용할 수 있다.
// 단 @Service, @Component, @Repository 등은 사용할 수 없다.
// 여기서는 컨트롤러만 사용하기 떄문에 선언한다.
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈 주입
    private MockMvc mvc; //웹 mvc테스트의 시작점

    @WithMockUser(roles = "USER")
    @Test
    public void hello() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) //url 값 주입
                .andExpect(status().isOk()) //http status 200 맞는지 확인
                .andExpect(content().string(hello)); //반환값이 hello 맞는지 확
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto테스트() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount))) //param test는 string만 허용된다. 날짜 숫자 데이터도 등록할때는 문자열로 변경해야한다.
                .andExpect(status().isOk())
                // Json 응답값을 필드별로 검증할 수 있는 메소드
                // $기준으로 필드명 명시함.
                // name, amount를 각각 $.name, $.amount로 검증
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
