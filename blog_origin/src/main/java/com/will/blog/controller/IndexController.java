package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import com.will.blog.service.BlogService;
import com.will.blog.service.SortService;
import com.will.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *  @name IndexController
 *  @Description navigation for front page :front index
 *  @author will7 Mao
 *  @Date  2020/8/18
 */

@Controller
@RequestMapping("/")
public class IndexController {
    private final static int DEFAULT_PAGE_SIZE = 3;
    private final static int DEFAULT_TAG_SIZE = 10;
    private final static int DEFAULT_SORT_SIZE = 10;
    private final static int DEFAULT_BLOG_SIZE = 10;

    @Autowired
    private BlogService blogService;
    @Autowired
    private SortService sortService;
    @Autowired
    private TagService tagService;

    @GetMapping("")
    public String index(@PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model){
        Page page = blogService.list(pageable,null);
        List<com.will.blog.entity.Sort> sorts = sortService.listTopSort(DEFAULT_SORT_SIZE);
        List<Tag> tags = tagService.listTopTag(DEFAULT_TAG_SIZE);
        List<Blog> blogs = blogService.listTopBlog(DEFAULT_BLOG_SIZE);
        model.addAttribute("page",page);
        model.addAttribute("sorts",sorts);
        model.addAttribute("tags",tags);
        model.addAttribute("blogs",blogs);
        return "index";
    }

    @PostMapping("/search")
    public String index(@PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, @RequestParam String query, Model model){
        Page page = blogService.search(pageable,query);
        model.addAttribute("page",page);
        model.addAttribute("query",query);
        return "search";
    }



}
