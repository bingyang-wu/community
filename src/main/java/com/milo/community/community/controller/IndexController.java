package com.milo.community.community.controller;

import com.milo.community.community.dto.QuestionDTO;
import com.milo.community.community.mapper.UserMapper;
import com.milo.community.community.model.User;
import com.milo.community.community.service.QuestionSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author milo
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;


    @Autowired
    private QuestionSevice questionSevice;

    @GetMapping("/")
    public String hello(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);

                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }

                    break;
                }
            }
        }

        // 问题列表
        List<QuestionDTO> questionList = questionSevice.list();
        model.addAttribute("questions", questionList);

        return "index";
    }
}
