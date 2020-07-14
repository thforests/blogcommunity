package com.example.blog.blogcommunity.controller;

import com.example.blog.blogcommunity.dto.NotificationDTO;
import com.example.blog.blogcommunity.enums.NotifictaionTypeEnum;
import com.example.blog.blogcommunity.model.User;
import com.example.blog.blogcommunity.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;


@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id") Long id){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(id, user);

        Long outer = notificationDTO.getOuterid();
        System.out.println(outer);
        if (NotifictaionTypeEnum.REPLY_QUESTION.getType()==notificationDTO.getType()||
        NotifictaionTypeEnum.REPLY_COMMENT.getType()==notificationDTO.getType()){
            return "redirect:/question/"+notificationDTO.getOuterid();
        }else{
            return "redirect:/";
        }


    }
}
