package com.will.blog.service.impl;

import com.will.blog.dao.BlogDao;
import com.will.blog.dao.SortDao;
import com.will.blog.entity.Blog;
import com.will.blog.entity.Sort;
import com.will.blog.service.BlogService;
import com.will.blog.service.SortService;
import com.will.blog.service.TagService;
import com.will.blog.util.MarkdownUtil;
import com.will.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Transactional
    @Override
    public Blog save(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogDao.save(blog);
    }

    @Override
    public Blog get(Long id) {
        return blogDao.getOne(id);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = get(id);
        if (blog==null){
            throw  new RuntimeException();
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = MarkdownUtil.blogExtensions(blog.getContent());
        b.setContent(content);
        return b;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        blogDao.deleteById(id);
    }

    @Transactional
    @Override
    public Blog update(Long id, Blog blog) {
        Blog b = blogDao.getOne(id);
        if (b==null){
            throw new RuntimeException();
        }
        BeanUtils.copyProperties(blog,b);
        return blogDao.save(b);
    }

    @Override
    public Page<Blog> list(Pageable pageable, BlogQuery blog) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (blog!=null) {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (blog.getTitle() != null && !"".equals(blog.getTitle())) {
                        predicateList.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                    }
                    if (blog.getSortId() != null) {
                        predicateList.add(criteriaBuilder.equal(root.<Sort>get("sort"), blog.getSortId()));
                    }
                    if (blog.getRecommend() != null && blog.getRecommend() != false) {
                        predicateList.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.getRecommend()));
                    }
                    criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                }
                return null;
            }
        },pageable);
    }

    @Override
    public List<Blog> listTopBlog(int i) {
        org.springframework.data.domain.Sort sort = org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.DEFAULT_DIRECTION,"updateTime");
        Pageable pageable = PageRequest.of(0,i, sort);
        return blogDao.ListTopBlog(pageable);
    }

    @Override
    public Page search(Pageable pageable, String query) {
        return blogDao.search("%"+query+"%",pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Long tagId) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Map<String, List<Blog>> achieveBlog() {
        List<String> years = blogDao.selectYears();
        Map<String,List<Blog>> map = new HashMap<>();
        years.forEach(y->{
            map.put(y,blogDao.findByYear(y));
        });
        return map;
    }

    @Override
    public Long count() {
        return blogDao.count();
    }

}
