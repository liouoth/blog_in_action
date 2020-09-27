package com.will.blog.service.impl;

import com.will.blog.dao.CommentDao;
import com.will.blog.entity.Comment;
import com.will.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<Comment> listRootByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "sendTime");
        List<Comment> comments = commentDao.findByBlogIdAndParentCommentEquals(blogId, null,sort);
        comments.stream().forEach(
                c->{
                    List<Comment> tempList = new ArrayList<>();
                    recursion(tempList,c);
                    c.setReplyComments(tempList);
                }
        );
        return comments;
    }

    public void recursion(List<Comment> rootReply,Comment comment){
        if (comment.getReplyComments()==null||comment.getReplyComments().size()==0){
            return;
        }
        comment.getReplyComments().stream().forEach(c->{
            recursion(rootReply,c);
            if (c.getParentComment()!=null){
                rootReply.add(c);
            }
        });
    }
}
