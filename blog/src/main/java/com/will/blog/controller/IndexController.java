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

    @RequestMapping("/admin/blogs_m")
    public String manager() {
        return "/admin/blogs_m";
    }

    @RequestMapping("/admin/blogs")
    public String blog() {
        return "/admin/blogs";
    }

    @RequestMapping("/admin/sorts")
    public String sorts() {
        return "/admin/sorts";
    }

    @RequestMapping("/admin/sorts_m")
    public String sorts_m(Model model) {
        model.addAttribute("sort",new Sort());
        return "/admin/sorts_m";
    }
    @RequestMapping("/admin/tags")
    public String tags() {
        return "/admin/tags";
    }

    @RequestMapping("/admin/tags_m")
    public String tags_m(Model model) {
        model.addAttribute("tag",new Sort());
        return "/admin/tags_m";
    }
}
