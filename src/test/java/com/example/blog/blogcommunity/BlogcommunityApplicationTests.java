package com.example.blog.blogcommunity;

import com.example.blog.blogcommunity.mapper.UserMapper;
import com.example.blog.blogcommunity.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogcommunityApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private UserMapper userMapper;

    @Test
    void test(){
        User user = userMapper.findById(65);

        System.out.println(user.getIconUrl());
    }

}
