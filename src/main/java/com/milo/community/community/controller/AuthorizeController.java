package com.milo.community.community.controller;

import com.milo.community.community.dto.AccessTokenDTO;
import com.milo.community.community.dto.GithubUser;
import com.milo.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权
 *
 * @author milo
 * @date 2020/3/19
 */
@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {

        System.out.println("code:" + code + ",state:" + state);

        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setClient_id(clientId);
        dto.setClient_secret(clientSecret);
        dto.setCode(code);
        dto.setRedirect_uri(redirectUri);
        dto.setState(state);

        String accessToken = githubProvider.getAccessToken(dto);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        System.out.println(githubUser.getName());

        if (githubUser != null) {
            request.getSession().setAttribute("user", githubUser);
            // 登录成功,写Cookie和Session
            return "redirect:/";
        } else {
            // 登录失败,重新登录
            return "redirect:/";
        }

    }
}
