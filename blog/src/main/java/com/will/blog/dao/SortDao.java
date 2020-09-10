package com.will.blog.dao;

import com.will.blog.entity.Sort;
import com.will.blog.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SortDao extends JpaRepository<Sort, Long> {
    Sort findByName(String name);

    @Query("select s from Sort s")
    List<Sort> topSort(Pageable pageable);
}
