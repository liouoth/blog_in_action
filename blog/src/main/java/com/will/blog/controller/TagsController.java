package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import com.will.blog.service.BlogService;
import com.will.blog.service.SortService;
import com.will.blog.service.TagService;
import com.will.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagsController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/{id}")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                 Pageable pageable,@PathVariable Long id, Model model) {
        List<Tag> tagList = tagService.listAll();
        model.addAttribute("tags",tagList);
        if (id==null||id==-1){
            if (tagList.size()>0){
                id=tagList.get(0).getId();
            }else{
                id = -1L;
            }
        }
        Page<Blog> page = blogService.listBlog(pageable,id);
        model.addAttribute("page",page);
        model.addAttribute("activeId",id);
        return "tags";
    }


}
