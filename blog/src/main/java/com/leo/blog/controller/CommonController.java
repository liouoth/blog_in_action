package com.leo.blog.controller;

import com.leo.blog.entity.ArticleEntity;
import com.leo.blog.entity.PostEntity;
import com.leo.blog.service.ArticleService;
import com.leo.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @name CommonController
 *  @Description common controller for page index
 *  @author will7 Mao
 *  @Date  2020/7/16
 */
@Controller
public class CommonController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public ModelAndView home(Model model){
        Map<String,Object> map = new HashMap<>();
        //list post
        List<ArticleEntity> articles = articleService.list(1,5);
        //list recently articles
        List<ArticleEntity> recentlyArticles = articleService.listRecently();
        //list top viewed articles
        List<ArticleEntity> topViewArticles = articleService.listTopView();
        //list recently post
        List<PostEntity> posts = postService.list(0,6);
        map.put("articles",articles);
        map.put("recentlyArticles",recentlyArticles);
        map.put("topViewArticles",topViewArticles);
        map.put("posts",posts);
        return new ModelAndView("home",map);
    }


}
