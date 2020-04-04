package com.gzy.oceanblog.admin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    private String intro;
    @Lob
    private String content;
    private String thumbPic;
    private Boolean recommend;

    private int views;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToMany
    private List<BlogTag> blogTags = new ArrayList<>();

    @ManyToMany
    private List<BlogType> blogTypes = new ArrayList<>();

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private BlogUser blogUser;

    @OneToMany(mappedBy = "blog")
    private List<BlogComment> blogComments = new ArrayList<>();

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getThumbPic() {
        return thumbPic;
    }

    public void setThumbPic(String thumbPic) {
        this.thumbPic = thumbPic;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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

    public List<BlogTag> getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(List<BlogTag> blogTags) {
        this.blogTags = blogTags;
    }

    public List<BlogType> getBlogTypes() {
        return blogTypes;
    }

    public void setBlogTypes(List<BlogType> blogTypes) {
        this.blogTypes = blogTypes;
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

    @JsonBackReference
    public void setBlogComments(List<BlogComment> blogComments) {
        this.blogComments = blogComments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", content='" + content + '\'' +
                ", thumbPic='" + thumbPic + '\'' +
                ", recommend=" + recommend +
                ", views=" + views +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", blogTags=" + blogTags +
                ", blogTypes=" + blogTypes +
                ", blogUser=" + blogUser +
                ", blogComments=" + blogComments +
                '}';
    }
}
