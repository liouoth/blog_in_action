package com.will.blog.controller.admin;

import com.will.blog.entity.Tag;
import com.will.blog.service.TagService;
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

@Controller("adminTagsController")
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/tags_m")
    public String tags_m(Model model) {
        model.addAttribute("tag",new com.will.blog.entity.Sort());
        return "/admin/tags_m";
    }

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3,page = 0,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",tagService.list(pageable));
        return "/admin/tags";
    }

    @GetMapping("/tags/delete/{id}/input")
    public String tags(@PathVariable(name = "id") Long id){
        tagService.delete(id);
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags")
    public String save(@Valid Tag tag, BindingResult result,
                        RedirectAttributes attributes){
        Tag temp = tagService.findByName(tag.getName());
        if (temp != null) {
            result.rejectValue("name","nameError","tag name already exist！");
        }

        if (result.hasErrors()){
            return "/admin/tags_m";
        }

        Tag t = tagService.save(tag);
        if (t==null){
            attributes.addFlashAttribute("message","新增失败！");
        }else {
            attributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String edit(@Valid Tag tag,
                       BindingResult result,
                        @PathVariable(name = "id") Long id,
                        RedirectAttributes attributes){
        Tag temp = tagService.findByName(tag.getName());
        if (temp != null) {
            result.rejectValue("name","nameError","tag name already exist！");
        }

        if (result.hasErrors()){
            return "/admin/tags_m";
        }

        Tag t = tagService.update(id,tag);
        if (t==null){
            attributes.addFlashAttribute("message","更新失败！");
        }else {
            attributes.addFlashAttribute("message","更新成功！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String update(@PathVariable(name = "id") Long id,Model model){
        model.addAttribute("tag",tagService.get(id));
        return "/admin/tags_m";
    }


}
