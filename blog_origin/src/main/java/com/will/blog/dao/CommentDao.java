package com.will.blog.dao;

import com.will.blog.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {
    List<Comment> findByBlogId(Long blogId, Sort sort);
    List<Comment> findByBlogIdAndParentCommentEquals(Long blogId,Comment parentComment,Sort sort);
}
