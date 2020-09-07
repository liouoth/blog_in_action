package com.will.blog.service.impl;

import com.will.blog.dao.BlogDao;
import com.will.blog.dao.SortDao;
import com.will.blog.entity.Blog;
import com.will.blog.entity.Sort;
import com.will.blog.service.BlogService;
import com.will.blog.service.SortService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog save(Blog blog) {
        return blogDao.save(blog);
    }

    @Override
    public Blog get(Long id) {
        return blogDao.getOne(id);
    }

    @Override
    public void delete(Long id) {
        blogDao.deleteById(id);
    }

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
    public Page<Blog> list(Pageable pageable,Blog blog) {

        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() !=null){
                    predicateList.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                if (blog.getSort() !=null && !"".equals(blog.getSort())){
                    predicateList.add(criteriaBuilder.equal(root.<Sort>get("sort"),blog.getSort().getId()));
                }
                if (blog.isRecommend()){
                    predicateList.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        },pageable);
    }
}
