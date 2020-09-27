package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import com.will.blog.service.BlogService;
import com.will.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AchievesController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/achieves")
    public String achieves(Model model){
        model.addAttribute("map",blogService.achieveBlog());
        model.addAttribute("count",blogService.count());
        return "achieves";
    }

}
