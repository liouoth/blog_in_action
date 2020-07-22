package com.leo.ui_in_action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  @name CommonController
 *  @Description common controller for page index
 *  @author will7 Mao
 *  @Date  2020/7/16
 */
@Controller
public class CommonController {
    @RequestMapping("/")
    public String home(){
        return "home";
    }


}
