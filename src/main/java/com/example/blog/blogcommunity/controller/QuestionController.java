package com.example.blog.blogcommunity.controller;

import com.example.blog.blogcommunity.dto.CommentCreateDTO;
import com.example.blog.blogcommunity.dto.CommentDTO;
import com.example.blog.blogcommunity.dto.QuestionDTO;
import com.example.blog.blogcommunity.enums.CommentTypeEnum;
import com.example.blog.blogcommunity.service.CommentService;
import com.example.blog.blogcommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);

        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments", comments);
        return "question";
    }

}
