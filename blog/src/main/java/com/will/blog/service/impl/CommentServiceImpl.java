package com.will.blog.service.impl;

import com.will.blog.dao.CommentDao;
import com.will.blog.entity.Comment;
import com.will.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment save(Comment comment) {
        Long parentId = comment.getParentComment().getId();
        Comment parentComment = null;
        if (parentId != null && parentId != -1) {
            parentComment = commentDao.getOne(parentId);
        }
        comment.setParentComment(parentComment);
        comment.setSendTime(new Date());
        return commentDao.save(comment);
    }

    @Override
    public List<Comment> listByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "sendTime");
        return commentDao.findByBlogId(blogId, sort);
    }
}
