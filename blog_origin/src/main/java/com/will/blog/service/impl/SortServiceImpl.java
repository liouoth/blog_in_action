package com.will.blog.service.impl;

import com.will.blog.dao.SortDao;
import com.will.blog.entity.Sort;
import com.will.blog.service.SortService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SortServiceImpl implements SortService {
    @Autowired
    private SortDao sortDao;

    @Override
    @Transactional
    public Sort save(Sort sort) {
        return sortDao.save(sort);
    }

    @Override
    public Sort get(Long id) {
        return sortDao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sortDao.deleteById(id);
    }

    @Override
    @Transactional
    public Sort update(Long id,Sort sort){
        Sort temp = sortDao.findById(id).get();
        if (temp==null){
            throw new RuntimeException();
        }
        BeanUtils.copyProperties(sort,temp);
        return sortDao.save(sort);
    }

    @Override
    public Sort findByName(String name) {
        return sortDao.findByName(name);
    }

    @Override
    public Page<Sort> list(Pageable pageable) {
        return sortDao.findAll(pageable);
    }

    @Override
    public List<Sort> listAll() {
        return sortDao.findAll();
    }

    @Override
    public List<Sort> listTopSort(Integer top) {
        org.springframework.data.domain.Sort sort = org.springframework.data.domain.Sort.by(
                org.springframework.data.domain.Sort.DEFAULT_DIRECTION,"blogList.size");
        Pageable pageable = PageRequest.of(0,top,sort);
        return sortDao.topSort(pageable);
    }
}
