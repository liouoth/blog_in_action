package com.will.blog.dao;

import com.will.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
