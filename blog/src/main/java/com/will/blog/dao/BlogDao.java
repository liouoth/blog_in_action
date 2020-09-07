package com.will.blog.dao;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogDao extends JpaRepository<Blog, Long> {
    Blog findByName(String name);
}
