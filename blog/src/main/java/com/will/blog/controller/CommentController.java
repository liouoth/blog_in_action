package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Comment;
import com.will.blog.service.BlogService;
import com.will.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @Value("${system.anonymous.avatar}")
    private String avatar;

    @GetMapping("/{id}")
    public String comments(@PathVariable Long id, Model model){
        model.addAttribute("comments",commentService.listByBlogId(id));
        return "blog :: blog_comment";
    }

    @PostMapping("")
    public String comment(Comment comment,Model model){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.get(blogId));
        comment.setAvatar(avatar);
        commentService.save(comment);
        return "redirect:/comment/"+comment.getBlog().getId();
    }
}
