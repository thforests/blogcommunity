package com.example.blog.blogcommunity.provider;

import com.alibaba.fastjson.JSON;
import com.example.blog.blogcommunity.dto.AccessTokenDTO;
import com.example.blog.blogcommunity.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenTDO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenTDO));
        Request request = new Request.Builder()
                                  .url("https://github.com/login/oauth/access_token")
                                  .post(body)
                                  .build();
        try (Response response = client.newCall(request).execute()) {
            String s = response.body().string();
            String token = s.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (Exception e) {
            log.error("getAccessToken error,{}", accessTokenTDO, e);
        }

        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                                  .url("https://api.github.com/user")
                                  .header("Authorization", "token " + accessToken)
                                  .build();
        try {
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            GithubUser githubUser = JSON.parseObject(s, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            log.error("getUser error,{}", accessToken, e);
        }
        return null;
    }
}
