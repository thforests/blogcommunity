package com.example.blog.blogcommunity.provider;


import com.example.blog.blogcommunity.exception.CustomizeErrorCode;
import com.example.blog.blogcommunity.exception.CustomizeException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class TencentCloudProvider {


    private static final String secretId = "AKIDveBZElyOS6bcN5c3JLB6pO6uUrycfK1Z";

    private static final String secretKey = "TPAh4ED40m593i6TajdMlx1xfBKI7jBP";

    private static final String localhost = "https://moopie-1302226282.cos.ap-nanjing.myqcloud.com";


    public String upload(File file, String fileName) {
        String generatedFileName;
        // .属于正则表达式,＋\\才能表示.
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length > 1) {
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else {
            return null;
        }

        String key = "images/"+generatedFileName;

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region("ap-nanjing");
        ClientConfig clientConfig = new ClientConfig(region);
        // 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = "moopie-1302226282";
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期

        //上传文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);


        Long dateTime = 1962631209000L;
        Date expiration = new Date(dateTime);
        System.out.println(expiration);
        req.setExpiration(expiration);
        URL url = cosClient.generatePresignedUrl(req);
        System.out.println(url.toString());

        if (url != null) {
            // 关闭OSSClient。
            String temp[] = url.toString().split("\\?");
            String endUrl = temp[0];
            cosClient.shutdown();
            System.out.println(endUrl);
            return endUrl;
        } else {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAILURE);
        }

    }

}


