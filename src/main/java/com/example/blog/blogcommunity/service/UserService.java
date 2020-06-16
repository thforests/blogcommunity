package com.example.blog.blogcommunity.service;

import com.example.blog.blogcommunity.mapper.UserMapper;
import com.example.blog.blogcommunity.model.User;
import com.example.blog.blogcommunity.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrupdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().
                andAccountIdEqualTo(user.getAccountId());
        List <User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            //插入
            user.setGmtModified(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setIconUrl(user.getIconUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
}
