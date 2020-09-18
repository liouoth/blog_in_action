package com.will.blog.dao;

import com.will.blog.entity.Blog;
import com.will.blog.entity.Sort;
import com.will.blog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor {
    @Query("select t from Blog t")
    List<Blog> ListTopBlog(Pageable pageable);

    @Query("select t from Blog t where title like ?1 or content like ?1")
    Page search(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog set views = views+1 where blogId = ?1")
    int view(Long blogId);

    List<Blog> findBySortEquals(Sort sort, Pageable pageable);


    @Query(value = "select date_format(b.update_time,'%Y') as year from blog b group by year Order by year desc"
    ,nativeQuery = true)
    List<String> selectYears();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
