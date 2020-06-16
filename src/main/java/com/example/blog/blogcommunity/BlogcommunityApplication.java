package com.example.blog.blogcommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.blog.blogcommunity.mapper")
public class BlogcommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogcommunityApplication.class, args);
    }

}
