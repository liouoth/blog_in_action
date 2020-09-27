package com.will.blog.controller;

import com.will.blog.entity.Blog;
import com.will.blog.service.BlogService;
import com.will.blog.service.SortService;
import com.will.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.util.List;

@Controller
@RequestMapping("/sorts")
public class SortController {
    @Autowired
    private SortService sortService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/{id}")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                 Pageable pageable,@PathVariable Long id, Model model) {
        List<com.will.blog.entity.Sort> sortList = sortService.listAll();
        model.addAttribute("sorts",sortList);
        if (id==null||id==-1){
            if (sortList.size()>0){
                id = sortList.get(0).getId();
            }else {
                id = -1L;
            }
        }
        BlogQuery query = new BlogQuery();
        query.setSortId(id);
        Page<Blog> page = blogService.list(pageable,query);
        model.addAttribute("page",page);
        model.addAttribute("activeId",id);
        return "sorts";
    }


}
