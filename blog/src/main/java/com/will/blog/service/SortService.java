package com.will.blog.service;

import com.will.blog.entity.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SortService {
    Sort save(Sort sort);
    Sort get(Long id);
    void delete(Long id);
    Sort update(Long id,Sort sort);
    Sort findByName(String name);
    Page<Sort> list(Pageable pageable);
}
