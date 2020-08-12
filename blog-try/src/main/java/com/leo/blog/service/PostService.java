package com.leo.blog.service;

import com.leo.blog.entity.ArticleEntity;
import com.leo.blog.entity.PostEntity;
import com.leo.blog.repository.ArticleRepository;
import com.leo.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<PostEntity> list(Integer index, Integer pageSize){
        Page<PostEntity> result = postRepository.findAll(new PageRequest(index,pageSize));
        if (result!=null){
            return result.getContent();
        }
        return new ArrayList<>();
    }

}
