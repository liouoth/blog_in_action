package com.will.blog.controller;

import com.will.blog.service.BlogService;
import com.will.blog.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/sorts")
public class SortController {
    @Autowired
    private SortService sortService;

    @Autowired
    private BlogService blogService;

    //sort edit
    @GetMapping("")
    public String sort(Model model){
        model.addAttribute("sorts",sortService.listAll());
        return "/sorts";
    }

    @PostMapping("/{id}")
    public String search(@PageableDefault(size = 1, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                     Pageable pageable,@PathVariable Long id,Model model){
        com.will.blog.entity.Sort sort = sortService.get(id);
        model.addAttribute("blog",blogService.listBySort(sort,pageable));
        return "sorts:sort_blog";
    }

}
