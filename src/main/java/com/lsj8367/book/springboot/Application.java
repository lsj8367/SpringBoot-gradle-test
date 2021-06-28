package com.lsj8367.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaAuditing //JPA Auditing 활성화
// JPA metamodel must not be empty! JPA Auditing 같이있어서 에러발생으로 제거
@SpringBootApplication
public class Application {
    public static void main(String[] args){ SpringApplication.run(Application.class, args); }
}
