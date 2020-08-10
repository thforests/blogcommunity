package com.example.blog.blogcommunity;

import com.example.blog.blogcommunity.enums.CommentTypeEnum;
import com.example.blog.blogcommunity.mapper.CommentExtMapper;
import com.example.blog.blogcommunity.mapper.QuestionExtMapper;
import com.example.blog.blogcommunity.mapper.QuestionMapper;
import com.example.blog.blogcommunity.mapper.UserMapper;
import com.example.blog.blogcommunity.model.Comment;
import com.example.blog.blogcommunity.model.Question;
import com.example.blog.blogcommunity.model.User;
import com.example.blog.blogcommunity.provider.TencentCloudProvider;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private TencentCloudProvider tencentCloudProvider;


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

    @Test
    void testQuestion(){
        Question question = questionMapper.selectByPrimaryKey(1L);
        System.out.println(question);
    }

    @Test
    void testCommentCount(){
        Comment parentComment = new Comment();
        parentComment.setId(137L);
        parentComment.setCommentCount(1);
       int i = commentExtMapper.incCommentCount(parentComment);
        System.out.println(i);

    }


    @Test
    void testQuestionCommentCount(){
        Question question = new Question();
        question.setId(1L);
        question.setCommentCount(1);
        int i = questionExtMapper.incCommentCount(question);
        System.out.println(i);

    }

    @Test
    void testFileupLoad(){
       // String url = tencentCloudProvider.upload("C:\\Users\\hp\\Desktop\\dream\\test.jpg","test.jpg");
        //System.out.println(,"");

    }

    @Test
    void testSpilt(){
      String s= "http://moopie-1302226282.cos.ap-nanjing.myqcloud.com/708698c5-125b-4b89-bb8a-17259bcb987f.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDEEzThfktUpuPedLUXLtE0VZOxFG1rWu6%26q-sign-time%3D1596703997%3B1596705797%26q-key-time%3D1596703997%3B1596705797%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D53bec0ae19e27be3c03d3caa9c040cda24d73dcc";
      String []ss = s.split("com/");

          System.out.println(ss[0]);

    }

}
