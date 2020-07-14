package com.example.blog.blogcommunity.service;

import com.example.blog.blogcommunity.dto.CommentDTO;
import com.example.blog.blogcommunity.enums.CommentTypeEnum;
import com.example.blog.blogcommunity.enums.NotificationStatusEnum;
import com.example.blog.blogcommunity.enums.NotifictaionTypeEnum;
import com.example.blog.blogcommunity.exception.CustomizeErrorCode;
import com.example.blog.blogcommunity.exception.CustomizeException;
import com.example.blog.blogcommunity.mapper.*;
import com.example.blog.blogcommunity.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
    public void insert(Comment comment,User commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARENT_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);

        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            //获取问题
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            System.out.println(question);
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //
            commentMapper.insert(comment);
            // 增加评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);

            // 创建通知
            createNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotifictaionTypeEnum.REPLY_COMMENT, question.getId());


        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);

            // 创建通知
            createNotify(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotifictaionTypeEnum.REPLY_QUESTION, question.getId());
        }

    }

    public List<CommentDTO> listByTargetId(Long id,CommentTypeEnum type) {
        CommentExample example = new CommentExample();
        example.createCriteria().
                andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
            example.setOrderByClause("gmt_create desc");
        List <Comment> comments = commentMapper.selectByExample(example);

        if (comments.size()==0){
            return new ArrayList <>();
        }
        //获取去重的评论人
        Set <Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> usersIds = new ArrayList <>();
        usersIds.addAll(commentators);

        //获取评论人并转化为map
        UserExample userExample = new UserExample();
        userExample.createCriteria().
                andIdIn(usersIds);
        List <User> users = userMapper.selectByExample(userExample);

        Map<Long,User>  userMap =  users.stream().collect(Collectors.toMap(user->user.getId(),user -> user));


        //转换comment 为commentDTO
        List <CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));

            return commentDTO;
        }).collect(Collectors.toList());

        return  commentDTOS;

    }

    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotifictaionTypeEnum notificationType, Long outerId) {
        Notification notification = new Notification();
        notification.setGmtCraete(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }


}
