package com.leo.blog.repository;

import com.leo.blog.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity,Long> {

    @Query(value = "select a.* from article a left join article_tag_assn ata on a.id = ata.aid left join tag t on ata.tid= t.id order by uploadTime limit 5",
            nativeQuery = true)
    List<ArticleEntity> listRecently();

    @Query(value = "select * from article order by views,likes limit 5",
            nativeQuery = true)
    List<ArticleEntity> listTopView();
}
