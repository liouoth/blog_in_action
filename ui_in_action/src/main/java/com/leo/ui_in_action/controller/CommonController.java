package com.leo.ui_in_action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
    @RequestMapping("/")
    public String home(){
        return "/home";
    }



}
