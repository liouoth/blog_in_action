package com.will.blog.service.impl;

import com.will.blog.dao.TagDao;
import com.will.blog.entity.Tag;
import com.will.blog.service.TagService;
import com.will.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

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

    @Override
    public List<Tag> parse(String ids) {
        List<Long> idList = new ArrayList<>();
        Arrays.stream(ids.split(",")).filter(id->id!=null&&!"".equals(ids)).forEach(
               id-> idList.add(Long.valueOf(id))
        );
        return tagDao.findAllById(idList);
    }

    public static String tag2String(List<Tag> list) {
        StringJoiner joiner = new StringJoiner(",","","");
        list.stream().filter(l->l.getId()!=null).forEach(l->{
            joiner.add(String.valueOf(l.getId()));
        });
        return joiner.toString();
    }

    @Override
    public List<Tag> listAll() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> listTopTag(int i) {
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION,"blogList.size");
        Pageable pageable = PageRequest.of(0,i, sort);
        return tagDao.listTopTag(pageable);
    }
}
