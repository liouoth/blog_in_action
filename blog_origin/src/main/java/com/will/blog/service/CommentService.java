package com.will.blog.service;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Comment;
import com.will.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CommentService {
    Comment save(Comment comment);
    List<Comment> listByBlogId(Long blogId);
    List<Comment> listRootByBlogId(Long blogId);
}
