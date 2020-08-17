package com.example.blog.blogcommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hp
 * @create 2020/8/13
 * @describe
 */

@Controller
public class LoginController {
    @RequestMapping("/loginchoice")
    public String loginOrRegister() {

        return "loginchoice";
    }
}
