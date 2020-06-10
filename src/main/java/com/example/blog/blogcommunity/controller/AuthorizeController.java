package com.example.blog.blogcommunity.controller;

import com.example.blog.blogcommunity.dto.AccessTokenDTO;
import com.example.blog.blogcommunity.dto.GithubUser;
import com.example.blog.blogcommunity.mapper.UserMapper;
import com.example.blog.blogcommunity.model.User;
import com.example.blog.blogcommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {


    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String client_Sercret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenTDO = new AccessTokenDTO();
        accessTokenTDO.setClient_id(clientId);
        accessTokenTDO.setCode(code);
        accessTokenTDO.setRedirect_uri(redirect_uri);
        accessTokenTDO.setClient_secret(client_Sercret);
        accessTokenTDO.setState(state);
        String accessToken =  githubProvider.getAccessToken(accessTokenTDO);
        GithubUser githubuser = githubProvider.getUser(accessToken);
        if (githubuser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubuser.getName());
            user.setAccountId(String.valueOf(githubuser.getId()));
            user.setGmtModified(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setIconUrl(githubuser.getAvatar_url());
            userMapper.insert(user);
         //   request.getSession().setAttribute("user",user);

            response.addCookie(new Cookie("token",token));

            return "redirect:/";

        }else{
            //登录失败，重新登录
            return "redirect:/";
        }

    }
}
