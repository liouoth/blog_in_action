package com.leo.thymeleaf_in_action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){
        Map map = new HashMap<>();
        map.put("name","12121");
        return new ModelAndView("index",map);
    }
    @GetMapping("/tags")
    public ModelAndView tags(ModelAndView modelAndView){
        return new ModelAndView("tags");
    }

}
