package com.will.blog.controller.admin;

import com.will.blog.entity.User;
import com.will.blog.service.UserService;
import com.will.blog.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    //admin index
    @RequestMapping("")
    public String adminIndex() {
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            User sessionUser = new User();
            BeanUtils.copyProperties(user,sessionUser,"password","salt");
            session.setAttribute("user", sessionUser);
            return "/admin/index";
        } else {
            redirectAttributes.addFlashAttribute("message", "user not exist or password not correct!");
            return "redirect:/admin";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
