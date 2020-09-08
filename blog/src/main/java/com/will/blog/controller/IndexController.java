package com.will.blog.controller;

import com.will.blog.entity.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  @name IndexController
 *  @Description navigation for template
 *  @author will7 Mao
 *  @Date  2020/8/18
 */

@Controller
@RequestMapping("/")
public class IndexController {

    //front index
    @GetMapping("")
    public String index(){
        return "index";
    }

    //admin index
    @RequestMapping("/admin")
    public String adminIndex() {
        return "/admin/login";
    }


}
