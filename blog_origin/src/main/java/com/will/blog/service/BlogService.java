package com.will.blog.service;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Sort;
import com.will.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface BlogService {
    Blog save(Blog blog);
    Blog get(Long id);
    Blog getAndConvert(Long id);
    void delete(Long id);
    Blog update(Long id, Blog blog);
    Page<Blog> list(Pageable pageable, BlogQuery blog);
    List<Blog> listTopBlog(int i);

    Page search(Pageable pageable, String query);

    Page<Blog> listBlog(Pageable pageable,Long tagId);

    Map<String,List<Blog>> achieveBlog();

    Long count();
}
