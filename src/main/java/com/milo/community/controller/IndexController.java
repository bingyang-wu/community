package com.milo.community.controller;

import com.milo.community.dto.PaginationDTO;
import com.milo.community.dto.QuestionDTO;
import com.milo.community.mapper.UserMapper;
import com.milo.community.model.User;
import com.milo.community.service.QuestionSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author milo
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionSevice questionSevice;

    @GetMapping("/")
    public String hello(@RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size, Model model, HttpServletRequest request) {

        // 问题列表
        PaginationDTO pagination = questionSevice.list(page, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
