package com.milo.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author milo
 */
@Controller
public class IndexConroller {

    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
