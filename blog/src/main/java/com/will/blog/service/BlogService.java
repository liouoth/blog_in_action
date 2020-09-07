package com.will.blog.service;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BlogService {
    Blog save(Blog blog);
    Blog get(Long id);
    void delete(Long id);
    Blog update(Long id, Blog blog);
    Blog findByName(String name);
    Page<Sort> list(Pageable pageable);
}
