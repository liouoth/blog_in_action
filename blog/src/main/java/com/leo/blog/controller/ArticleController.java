package com.leo.blog.controller;

import com.leo.blog.common.ApiEntity;
import com.leo.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  @name ArticleController
 *  @Description controller for article
 *  @author will7 Mao
 *  @Date  2020/7/16
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public ApiEntity list(@RequestParam(name = "index",defaultValue = "1") Integer index
            , @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize){
        return ApiEntity.success(articleService.list(index, pageSize));
    }

}
