package com.example.blog.blogcommunity.controller;

import com.example.blog.blogcommunity.cache.TagCache;
import com.example.blog.blogcommunity.service.UserService;
import generate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

/**
 * @author hp
 * @create 2020/8/13
 * @describe
 */

@Controller
public class LoginController {
    @Autowired
    private UserService userService;


    @RequestMapping("/loginchoice")
    public String loginOrRegister() {
        return "loginchoice";
    }


    @PostMapping("/login")
    public String login(@RequestParam("login_username") String username,
                        @RequestParam("login_pwd") String password,
                        @RequestParam("login_code") String code,
                        Model model) {

        model.addAttribute("login_username", username);
        model.addAttribute("login_pwd", password);
        model.addAttribute("login_code", code);

        //前后端都需要验证，此处前端为了方便没有做判断
        if (username == null || username == "") {
            model.addAttribute("error", "用户名不能为空");
            return "/loginchioce";
        }
        if (password == null || password == "") {
            model.addAttribute("error", "密码不能为空");
            return "/loginchioce";
        }
        if (code == null || code == "") {
            model.addAttribute("error", "验证码不能为空");
            return "/loginchioce";
        }

        int state = userService.registerUser(username,password);
        if (state==1){
            
        }


        return "/loginchioce";

    }
/*

    @PostMapping()
    public String regis(@RequestParam("regis_username") String username,
                        @RequestParam("regis_pwd") String password,
                        @RequestParam("regis_pwd2") String passwordAg,
                        @RequestParam("regis_code") String code){

        if (password==passwordAg){
            int state = userService.registerUser(username,passwordAg);
            if (state==1){
                return "/";
            }else {

            }
        }

        return "loginchoice";
    }
*/


}
