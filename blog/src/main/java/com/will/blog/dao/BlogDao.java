package com.will.blog.dao;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor {
}
