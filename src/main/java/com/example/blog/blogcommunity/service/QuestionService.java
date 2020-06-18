package com.example.blog.blogcommunity.service;

import com.example.blog.blogcommunity.dto.PaginationDTO;
import com.example.blog.blogcommunity.dto.QuestionDTO;
import com.example.blog.blogcommunity.exception.CustomizeErrorCode;
import com.example.blog.blogcommunity.exception.CustomizeException;
import com.example.blog.blogcommunity.mapper.QuestionExtMapper;
import com.example.blog.blogcommunity.mapper.QuestionMapper;
import com.example.blog.blogcommunity.mapper.UserMapper;
import com.example.blog.blogcommunity.model.Question;
import com.example.blog.blogcommunity.model.QuestionExample;
import com.example.blog.blogcommunity.model.User;
import org.apache.ibatis.session.RowBounds;
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
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        QuestionExample example = new QuestionExample();
        Integer totalCount = (int) questionMapper.countByExample(example);

        Integer totalPage;
        if (totalCount % size != 0) {
            totalPage = totalCount / size + 1;
        } else {
            totalPage = totalCount / size;
        }


        if (page <= 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }


        paginationDTO.setPagination(totalPage, page);

        //indexController中设置默认值page=1，size=5
        Integer offset = size * (page - 1);

        QuestionExample questionExample = new QuestionExample();
        List <Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List <QuestionDTO> questionDTOList = new ArrayList <>();


        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question的属性快速拷贝到questionDTO
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }


    public PaginationDTO listmine(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample ques = new QuestionExample();
        ques.createCriteria().
                andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(ques);

        Integer totalPage;
        if (totalCount % size != 0) {
            totalPage = totalCount / size + 1;
        } else {
            totalPage = totalCount / size;
        }


        if (page <= 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        //indexController中设置默认值page=1，size=5
        Integer offset = size * (page - 1);

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().
                andCreatorEqualTo(userId);
        List <Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List <QuestionDTO> questionDTOList = new ArrayList <>();


        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question的属性快速拷贝到questionDTO
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }


    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            return questionDTO;
    }

        public void createOrUpdate(Question question){
            if (question.getId() == null) {
                //创建
                question.setGmtCreate(System.currentTimeMillis());
                question.setGmtModified(question.getGmtModified());
                question.setViewCount(0);
                question.setLikeCount(0);
                question.setCommentCount(0);
                questionMapper.insert(question);
            } else {
                //更新
                Question updateQuestion = new Question();
                updateQuestion.setGmtCreate(System.currentTimeMillis());
                updateQuestion.setTitle(question.getTitle());
                updateQuestion.setTag(question.getTag());
                QuestionExample example = new QuestionExample();
                example.createCriteria().
                        andCreatorEqualTo(question.getId());
                int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
                if (updated != 1) {
                    throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
                }
            }
        }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);

    }
}
