package com.milo.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author milo
 */
@Controller
public class InexController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
