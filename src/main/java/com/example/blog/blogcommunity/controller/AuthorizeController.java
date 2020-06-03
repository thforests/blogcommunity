package com.example.blog.blogcommunity.controller;

import com.example.blog.blogcommunity.dto.AccessTokenDTO;
import com.example.blog.blogcommunity.dto.GithubUser;
import com.example.blog.blogcommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenTDO = new AccessTokenDTO();
        accessTokenTDO.setClient_id("fe9463ce11cae635aec0");
        accessTokenTDO.setCode(code);
        accessTokenTDO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenTDO.setClient_secret("2c163bda1ed91d0a7b5d4e8194ca87fa8efb8d8d");
        accessTokenTDO.setState(state);
        String accessToken =  githubProvider.getAccessToken(accessTokenTDO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";

    }
}
