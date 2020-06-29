package com.example.blog.blogcommunity.mapper;

import com.example.blog.blogcommunity.model.Question;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question record);

    List <Question> selectRelated(Question question);
}