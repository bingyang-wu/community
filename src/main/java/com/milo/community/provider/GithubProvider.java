package com.milo.community.provider;

import com.alibaba.fastjson.JSON;
import com.milo.community.dto.AccessTokenDTO;
import com.milo.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author milo
 * @date 2020/3/20
 */
@Component
public class GithubProvider {


    public String getAccessToken(AccessTokenDTO dto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        String content = JSON.toJSONString(dto);
        System.out.println(content);

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println("String:" + string);
            String token = string.split("&")[0].split("=")[1];
            System.out.println("截取后的Token" + token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String user = response.body().string();
            GithubUser githubUser = JSON.parseObject(user, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            System.out.println("根据Token获取Github用户信息异常");
        }
        return null;
    }


}
