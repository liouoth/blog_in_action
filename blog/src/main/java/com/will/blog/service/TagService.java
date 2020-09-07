package com.will.blog.service;

import com.will.blog.entity.Sort;
import com.will.blog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TagService {
    Tag save(Tag tag);
    Tag get(Long id);
    void delete(Long id);
    Tag update(Long id, Tag tag);
    Tag findByName(String name);
    Page<Tag> list(Pageable pageable);
}
