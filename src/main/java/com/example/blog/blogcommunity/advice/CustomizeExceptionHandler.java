package com.example.blog.blogcommunity.advice;

import com.alibaba.fastjson.JSON;
import com.example.blog.blogcommunity.dto.ResultDTO;
import com.example.blog.blogcommunity.exception.CustomizeErrorCode;
import com.example.blog.blogcommunity.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@ControllerAdvice
@Slf4j
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentTye = request.getContentType();
        if ("application/json".equals(contentTye)) {
            ResultDTO resultDTO;
            //返回json
            if (e instanceof CustomizeException) {
                resultDTO =  ResultDTO.errorOf((CustomizeException) e);
            } else {
                log.error("handle error", e);
                resultDTO =  ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.setStatus(200);
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSON.toJSONString(resultDTO));
                printWriter.close();
            }catch (IOException ioe){

            }
            return null;
        } else {
            //错误页面跳转

            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                log.error("handle error", e);
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }

            return new ModelAndView("error");
        }
    }


}
