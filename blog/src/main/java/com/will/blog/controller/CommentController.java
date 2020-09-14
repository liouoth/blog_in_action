package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Comment;
import com.will.blog.service.BlogService;
import com.will.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @Value("${system.anonymous.avatar}")
    private String avatar;

    @GetMapping("/comments/{id}")
    public String comments(@PathVariable Long id, Model model){
        model.addAttribute("comments",commentService.listByBlogId(id));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String comment(Comment comment){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.get(blogId));
        comment.setAvatar(avatar);
        commentService.save(comment);
        return "redirect:/comments/"+blogId;
    }
}
