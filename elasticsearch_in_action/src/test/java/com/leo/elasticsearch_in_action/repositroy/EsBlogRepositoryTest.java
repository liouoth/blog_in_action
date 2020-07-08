package com.leo.elasticsearch_in_action.repositroy;

import com.leo.elasticsearch_in_action.entity.EsBlog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)

public class EsBlogRepositoryTest {
    @Autowired
    private EsBlogRepository esBlogRepository;

    @Before
    public void init(){
        esBlogRepository.deleteAll();
        esBlogRepository.save(new EsBlog("静夜思","游子思乡","床前明月光疑似地上霜举头望明月低头思故乡"));
        esBlogRepository.save(new EsBlog("渔歌子","游子","窗含西岭千秋雪门泊东吴万里船"));
        esBlogRepository.save(new EsBlog("悯农","珍惜","谁知盘中餐粒粒皆辛苦"));
    }

    @Test
    public void findByTitleContainingOrSummaryContainingOrContentContaining() {
        Pageable pageable = new PageRequest(1,10);
        Page<EsBlog> blogs = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining("静夜","游子","床",pageable);
        System.out.println(blogs.getTotalElements());
        Assert.assertEquals(2,blogs.getTotalElements());
//        assertThat(blogs.getTotalElements()); assertThat 是junit4.4的新特性
    }

}