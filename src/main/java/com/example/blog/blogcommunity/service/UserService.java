package com.example.blog.blogcommunity.service;

import com.example.blog.blogcommunity.mapper.UserMapper;
import com.example.blog.blogcommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrupdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser==null){
            //插入
            user.setGmtModified(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(dbUser);
        }else{
            //更新
            user.setGmtModified(System.currentTimeMillis());
            dbUser.setIconUrl(user.getIconUrl());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);

        }
    }
}
