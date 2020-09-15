package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import com.will.blog.service.BlogService;
import com.will.blog.service.SortService;
import com.will.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@Controller
public class meController {
    @RequestMapping("/me")
    public String me(){
        return "about";
    }
}
