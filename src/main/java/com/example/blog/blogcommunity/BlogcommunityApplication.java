package com.example.blog.blogcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication

public class BlogcommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogcommunityApplication.class, args);
    }

}
