package com.example.blog.blogcommunity.mapper;

import com.example.blog.blogcommunity.dto.QuestionQueryDTO;
import com.example.blog.blogcommunity.model.Question;
import com.example.blog.blogcommunity.model.QuestionExample;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question record);

    List <Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);

    int insertAfterSearch(Question question);
}