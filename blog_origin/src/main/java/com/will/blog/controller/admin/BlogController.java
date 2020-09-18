package com.will.blog.controller.admin;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import com.will.blog.entity.User;
import com.will.blog.service.BlogService;
import com.will.blog.service.SortService;
import com.will.blog.service.TagService;
import com.will.blog.service.impl.TagServiceImpl;
import com.will.blog.util.BeanUtil;
import com.will.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller("adminBlogController")
@RequestMapping("/admin")
public class BlogController {
    private final static int DEFAULT_PAGE_SIZE = 2;

    @Autowired
    private BlogService blogService;
    @Autowired
    private SortService sortService;
    @Autowired
    private TagService tagService;

    //blog manager
    @GetMapping("/blogs_m")
    public String blogs(@PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model) {
        Page page = blogService.list(pageable,blog);
        model.addAttribute("page",page);
        model.addAttribute("sorts",sortService.listAll());
        return "/admin/blogs_m";
    }

    //enter a new blog page
    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("blog",new Blog());
        model.addAttribute("tags",tagService.listAll());
        model.addAttribute("sorts",sortService.listAll());
        return "/admin/blog";
    }

    //enter blog edit page
    @GetMapping("/blog/{id}/input")
    public String blogEdit(@PathVariable Long id ,Model model) {
        Blog blog = blogService.get(id);
        blog.setSort(sortService.get(blog.getSort().getId()));
        blog.setTagIds(TagServiceImpl.tag2String(blog.getTags()));
        model.addAttribute("blog",blog);
        model.addAttribute("tags",tagService.listAll());
        model.addAttribute("sorts",sortService.listAll());
        return "/admin/blog";
    }

    //delete blog
    @GetMapping("/blog/{id}/delete")
    public String blogEdit(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/admin/blogs_m";
    }

    //search blog
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model) {
        Page page = blogService.list(pageable,blog);
        model.addAttribute("page",page);
        return "/admin/blogs_m :: blog_list";
    }

    // edit/new blog
    @PostMapping("/blog")
    public String save(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setSort(sortService.get(blog.getSort().getId()));
        blog.setTags(tagService.parse(blog.getTagIds()));
        Blog b = null;
        if (blog.getId()==null) {
            b = blogService.save(blog);
        }else {
            Blog oldVersion = blogService.get(blog.getId());
            BeanUtils.copyProperties(blog,oldVersion,BeanUtil.selectNullProp(blog));
            b = blogService.update(oldVersion.getId(),oldVersion);
        }
        if (b==null){
            attributes.addFlashAttribute("message","操作失败！");
        }else{
            attributes.addFlashAttribute("message","操作成功！");
        }
        return "redirect:/admin/blogs_m";
    }
}
