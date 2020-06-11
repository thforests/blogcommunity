package com.example.blog.blogcommunity.service;

import com.example.blog.blogcommunity.dto.PaginationDTO;
import com.example.blog.blogcommunity.dto.QuestionDTO;
import com.example.blog.blogcommunity.mapper.QuestionMapper;
import com.example.blog.blogcommunity.mapper.UserMapper;
import com.example.blog.blogcommunity.model.Question;
import com.example.blog.blogcommunity.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);

        if (page <= 1) {
            page = 1;
        }

        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        //indexController中设置默认值page=1，size=5
        Integer offset = size * (page - 1);

        List <Question> questions = questionMapper.list(offset, size);
        List <QuestionDTO> questionDTOList = new ArrayList <>();


        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question的属性快速拷贝到questionDTO
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }
}
