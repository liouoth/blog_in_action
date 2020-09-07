package com.will.blog.service.impl;

import com.will.blog.dao.BlogDao;
import com.will.blog.dao.SortDao;
import com.will.blog.entity.Sort;
import com.will.blog.service.SortService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogServiceImpl implements SortService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public Sort save(Sort sort) {
        return null;
    }

    @Override
    public Sort get(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Sort update(Long id, Sort sort) {
        return null;
    }

    @Override
    public Sort findByName(String name) {
        return null;
    }

    @Override
    public Page<Sort> list(Pageable pageable) {
        return null;
    }
}
