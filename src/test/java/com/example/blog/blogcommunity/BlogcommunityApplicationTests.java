package com.example.blog.blogcommunity;

import com.example.blog.blogcommunity.enums.CommentTypeEnum;
import com.example.blog.blogcommunity.mapper.QuestionMapper;
import com.example.blog.blogcommunity.mapper.UserMapper;
import com.example.blog.blogcommunity.model.Question;
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

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    void test(){
        User user = userMapper.selectByPrimaryKey(65L);

        System.out.println(user.getIconUrl());
    }


    @Test
    void testQuestionCount(){
        Question question = new Question();
        question.setId(2L);
        question.setTag("1");
        question.setTitle("test");
        question.setDescription("22");
        System.out.println(question);
    }


    @Test
    void testEnum(){
        System.out.println(CommentTypeEnum.COMMENT.getType());
        Integer m = 123;
        Integer n = 123;
        System.out.println(m == n);
    }
}
