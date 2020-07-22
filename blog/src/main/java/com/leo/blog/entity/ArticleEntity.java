package com.leo.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "article")
@ApiModel("文章实体类")
public class ArticleEntity implements Serializable {
    @Id
    @GeneratedValue
    @ApiModelProperty("用户 id")
    private Long id;
    private String title;
    private String summary;
    private String content;
    private Long views = 0L;
    private Long likes = 0L;
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date uploadTime;
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updateTime;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="article_tag_assn",joinColumns = {@JoinColumn(name = "aid")},
            inverseJoinColumns = {@JoinColumn(name = "tid")}
    )
    private Set<TagEntity> tags;

    protected ArticleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Set<TagEntity> getTags() {
        return tags;
    }

    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
    }
}

