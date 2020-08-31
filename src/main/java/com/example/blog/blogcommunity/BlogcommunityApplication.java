package com.example.blog.blogcommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.example.blog.blogcommunity.mapper")
@EnableScheduling
public class BlogcommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogcommunityApplication.class, args);
    }

}
