package com.will.blog.dao;

import com.will.blog.entity.Sort;
import com.will.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SortDao extends JpaRepository<Sort, Long> {
    Sort findByName(String name);
}
