package com.example.blog.blogcommunity.controller;

import com.example.blog.blogcommunity.mapper.QuestionMapper;
import com.example.blog.blogcommunity.model.Question;
import com.example.blog.blogcommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model

    ) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        //前后端都需要验证，此处前端为了方便没有做判断
        if (title==null||title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (tag==null||tag == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        if (description==null||description == ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }



        Cookie[] cookies = request.getCookies();

        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            model.addAttribute("error", "用户未登录");
            //若出现问题，则回到当前页面
            return "publish";
        }


        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtModified());

        questionMapper.create(question);
        //发布成功，回到首页
        return "redirect:/";
    }
}