package com.gzy.oceanblog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String thumb;
    private String flag;
    private Integer views;
    private Boolean appreciation;
    private Boolean shareState;
    private Boolean commentEnabled;
    private Boolean publishState;
    private Boolean recommend;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToMany
    private List<BlogType> blogTypes = new ArrayList<>();

    @ManyToMany
    private List<BlogTag> blogTags = new ArrayList<>();

    @ManyToOne
    private BlogUser blogUser;

    @OneToMany(mappedBy = "blog")
    private List<BlogComment> blogComments = new ArrayList<>();

    public Blog() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public Boolean getShareState() {
        return shareState;
    }

    public void setShareState(Boolean shareState) {
        this.shareState = shareState;
    }

    public Boolean getCommentEnabled() {
        return commentEnabled;
    }

    public void setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
    }

    public Boolean getPublishState() {
        return publishState;
    }

    public void setPublishState(Boolean publishState) {
        this.publishState = publishState;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<BlogType> getBlogTypes() {
        return blogTypes;
    }

    public void setBlogTypes(List<BlogType> blogTypes) {
        this.blogTypes = blogTypes;
    }

    public List<BlogTag> getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(List<BlogTag> blogTags) {
        this.blogTags = blogTags;
    }

    public BlogUser getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(BlogUser blogUser) {
        this.blogUser = blogUser;
    }

    public List<BlogComment> getBlogComments() {
        return blogComments;
    }

    public void setBlogComments(List<BlogComment> blogComments) {
        this.blogComments = blogComments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", thumb='" + thumb + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareState=" + shareState +
                ", commentEnabled=" + commentEnabled +
                ", publishState=" + publishState +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
