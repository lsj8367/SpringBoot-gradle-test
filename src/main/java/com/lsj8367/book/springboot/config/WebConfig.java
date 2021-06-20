package com.lsj8367.book.springboot.config;

import com.lsj8367.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //LoginUserArgumentResolver가 스프링에서 인식될수 있도록 이 클래스를 생성
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers메소드를 통해 추가해야 한다.
        //다른 HandlerMethodArgumentResolver가 필요하다면 같은 방식으로 추가해준다.
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
