package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Comment;
import com.will.blog.entity.User;
import com.will.blog.service.BlogService;
import com.will.blog.service.CommentService;
import com.will.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @Value("${system.anonymous.avatar}")
    private String avatar;

    @GetMapping("/comments/{id}")
    public String comments(@PathVariable Long id, Model model){
        List<Comment> comments =  commentService.listRootByBlogId(id);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String comment(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        User user = (User) session.getAttribute("user");
        User u = null;
        if (user!=null) {
            u = userService.get(user.getId());
        }
        if (u != null) {
            comment.setAvatar(u.getAvatar());
            comment.setIsAdmin(true);
        }else{
            comment.setAvatar(avatar);
            comment.setIsAdmin(false);
        }
        comment.setBlog(blogService.get(blogId));
        commentService.save(comment);
        return "redirect:/comments/"+blogId;
    }
}
