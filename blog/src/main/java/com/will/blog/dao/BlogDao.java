package com.will.blog.dao;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor {
    @Query("select t from Blog t")
    List<Blog> ListTopBlog(Pageable pageable);

    @Query("select t from Blog t where title like ?1 or content like ?1")
    Page search(String query,Pageable pageable);
}
