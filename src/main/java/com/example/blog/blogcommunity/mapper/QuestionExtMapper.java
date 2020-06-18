package com.example.blog.blogcommunity.mapper;

import com.example.blog.blogcommunity.model.Question;

public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);
}