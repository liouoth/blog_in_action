package com.leo.blog.service;

import com.leo.blog.common.ApiEntity;
import com.leo.blog.entity.ArticleEntity;
import com.leo.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleEntity> list(Integer index, Integer pageSize){
        Page<ArticleEntity> result = articleRepository.findAll(new PageRequest(index,pageSize));
        if (result!=null){
            return result.getContent();
        }
        return new ArrayList<>();
    }

    public List<ArticleEntity> listRecently(){
        List<ArticleEntity> result = articleRepository.listRecently();
        if (result!=null){
            return result;
        }
        return new ArrayList<>();
    }

    public List<ArticleEntity> listTopView() {
        List<ArticleEntity> result = articleRepository.listTopView();
        if (result!=null){
            return result;
        }
        return new ArrayList<>();
    }
}
