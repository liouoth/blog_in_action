package com.leo.jpa_in_action.controller;

import com.leo.jpa_in_action.entity.User;
import com.leo.jpa_in_action.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/save")
    public String save(){
        User user = new User(null,"leo","mwqyykl@gmail.com");
        User result = userRepository.save(user);
        return result.toString();
    }
}
