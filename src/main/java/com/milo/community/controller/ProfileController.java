package com.milo.community.controller;

import com.milo.community.dto.PaginationDTO;
import com.milo.community.mapper.UserMapper;
import com.milo.community.model.Question;
import com.milo.community.model.User;
import com.milo.community.service.QuestionSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionSevice questionSevice;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);

                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }

                    break;
                }
            }
        }

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("section", action);
        if ("questions".equals(action)) {
            model.addAttribute("sectionName", "我的提问");
        }
        if ("replies".equals(action)) {
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDTO paginationDTO = questionSevice.listByUserId(user.getId(), page, size);

        model.addAttribute("pagination", paginationDTO);

        return "profile";
    }
}
