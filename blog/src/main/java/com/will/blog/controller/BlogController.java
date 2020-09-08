package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import com.will.blog.service.BlogService;
import com.will.blog.service.SortService;
import com.will.blog.service.TagService;
import com.will.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private SortService sortService;

    @GetMapping("/blogs_m")
    public String blogs(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model) {
        Page page = blogService.list(pageable,blog);
        model.addAttribute("page",page);
        model.addAttribute("sorts",sortService.listAll());
        return "/admin/blogs_m";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model) {
        Page page = blogService.list(pageable,blog);
        model.addAttribute("page",page);
        return "/admin/blogs_m :: blog_list";
    }


}
