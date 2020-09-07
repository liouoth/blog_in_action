package com.will.blog.service.impl;

import com.will.blog.dao.TagDao;
import com.will.blog.entity.Tag;
import com.will.blog.service.TagService;
import com.will.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    @Transactional
    public Tag save(Tag Tag) {
        return tagDao.save(Tag);
    }

    @Override
    public Tag get(Long id) {
        return tagDao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    @Transactional
    public Tag update(Long id,Tag Tag){
        Tag temp = tagDao.findById(id).get();
        if (temp==null){
            throw new RuntimeException();
        }
        BeanUtils.copyProperties(Tag,temp);
        return tagDao.save(Tag);
    }

    @Override
    public Tag findByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public Page<Tag> list(Pageable pageable) {
        return tagDao.findAll(pageable);
    }
}
