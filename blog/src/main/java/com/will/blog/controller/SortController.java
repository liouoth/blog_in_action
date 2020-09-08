package com.will.blog.controller;

import com.will.blog.entity.User;
import com.will.blog.service.SortService;
import com.will.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class SortController {
    @Autowired
    private SortService sortService;

    @RequestMapping("/sorts_m")
    public String sorts_m(Model model) {
        model.addAttribute("sort",new com.will.blog.entity.Sort());
        return "/admin/sorts_m";
    }

    @GetMapping("/sorts")
    public String sorts(@PageableDefault(size = 3,page = 0,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",sortService.list(pageable));
        return "/admin/sorts";
    }

    @GetMapping("/sorts/delete/{id}/input")
    public String sorts(@PathVariable(name = "id") Long id){
        sortService.delete(id);
        return "redirect:/admin/sorts";
    }

    @PostMapping("/sorts")
    public String save(@Valid com.will.blog.entity.Sort sort, BindingResult result,
                        RedirectAttributes attributes){
        com.will.blog.entity.Sort temp = sortService.findByName(sort.getName());
        if (temp != null) {
            result.rejectValue("name","nameError","sort name already exist！");
        }

        if (result.hasErrors()){
            return "/admin/sorts_m";
        }

        com.will.blog.entity.Sort s = sortService.save(sort);
        if (s==null){
            attributes.addFlashAttribute("message","新增失败！");
        }else {
            attributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/sorts";
    }

    @PostMapping("/sorts/{id}")
    public String edit(@Valid com.will.blog.entity.Sort sort,
                       BindingResult result,
                        @PathVariable(name = "id") Long id,
                        RedirectAttributes attributes){
        com.will.blog.entity.Sort temp = sortService.findByName(sort.getName());
        if (temp != null) {
            result.rejectValue("name","nameError","sort name already exist！");
        }

        if (result.hasErrors()){
            return "/admin/sorts_m";
        }

        com.will.blog.entity.Sort s = sortService.update(id,sort);
        if (s==null){
            attributes.addFlashAttribute("message","更新失败！");
        }else {
            attributes.addFlashAttribute("message","更新成功！");
        }
        return "redirect:/admin/sorts";
    }

    @GetMapping("/sorts/{id}/input")
    public String update(@PathVariable(name = "id") Long id,Model model){
        model.addAttribute("sort",sortService.get(id));
        return "/admin/sorts_m";
    }


}
