package com.will.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  @name IndexController
 *  @Description navigation for template
 *  @author will7 Mao
 *  @Date  2020/8/18
 */

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }

    @GetMapping("/sorts")
    public String sorts(){
        return "sorts";
    }


}
