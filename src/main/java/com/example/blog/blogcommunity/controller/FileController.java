package com.example.blog.blogcommunity.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.blog.blogcommunity.dto.FileDTO;
import com.example.blog.blogcommunity.provider.TencentCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private TencentCloudProvider tencentCloudProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    @CrossOrigin
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("editormd-image-file");

        //JSONObject jsonObject = new JSONObject();
        try {
            File file = MultiFiletoFile(multipartFile);
            String urlFileName = tencentCloudProvider.upload(file, multipartFile.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setMessage("上传成功");
            fileDTO.setUrl(urlFileName);
            return fileDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/wechat.png");
        return fileDTO;
    }

    public static File MultiFiletoFile(MultipartFile multipartFile) throws IOException {
        //MultipartFile是Spring提供的一个接口
        // 用来接收multipart／form-data类型
        // 请求方式中即将上传的文件，为处理或保存文件，MultipartFile和File需要经常进行转换。
        File file = File.createTempFile("null", "temp");
        //将multifile和file进行互相转换
        multipartFile.transferTo(file);

        /**1.delete()方法：
         当调用delete()方法时，直接删除文件，不管该文件是否存在，一经调用立即执行；
         2.deleteOnExit()方法：
         当调用deleteOnExit()方法时，只是相当于对deleteOnExit（）作一个声明，
         当程序运行结束，JVM终止时才真正调用deleteOnExit()方法实现删除操作。
         即该方法是将删除的命令缓存了一下，到服务停止的时候再进行操作！
         * */
        file.deleteOnExit();
        return file;

    }

}
