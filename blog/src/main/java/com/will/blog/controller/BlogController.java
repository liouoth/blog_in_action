package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import com.will.blog.entity.User;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private SortService sortService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs_m")
    public String blogs(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model) {
        Page page = blogService.list(pageable,blog);
        model.addAttribute("page",page);
        model.addAttribute("sorts",sortService.listAll());
        return "/admin/blogs_m";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("sorts",sortService.listAll());
        model.addAttribute("tags",tagService.listAll());
        return "/admin/blog";
    }

    @GetMapping("/blog/input")
    public String blogInput(Model model) {
        model.addAttribute("blog",new Blog());
        model.addAttribute("tags",tagService.listAll());
        model.addAttribute("sorts",sortService.listAll());
        return "/admin/blog";
    }

    @GetMapping("/blog/{id}/input")
    public String blogInput(@PathVariable Long id ,Model model) {
        model.addAttribute("blog",blogService.get(id));
        model.addAttribute("tags",tagService.listAll());
        model.addAttribute("sorts",sortService.listAll());
        return "/admin/blog";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model) {
        Page page = blogService.list(pageable,blog);
        model.addAttribute("page",page);
        return "/admin/blogs_m :: blog_list";
    }


    @PostMapping("/blog")
    public String save(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setSort(sortService.get(blog.getSort().getId()));
        blog.setTags(tagService.parse(blog.getTagIds()));
        Blog b = blogService.save(blog);
        if (b==null){
            attributes.addFlashAttribute("message","操作失败！");
        }else{
            attributes.addFlashAttribute("message","操作成功！");
        }
        return "redirect:/admin/blogs_m";
    }
}
