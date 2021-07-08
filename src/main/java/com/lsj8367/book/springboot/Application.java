package com.lsj8367.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

//@EnableJpaAuditing //JPA Auditing 활성화
// JPA metamodel must not be empty! JPA Auditing 같이있어서 에러발생으로 제거
@SpringBootApplication
public class Application {
    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args){ SpringApplication.run(Application.class, args); }
}
