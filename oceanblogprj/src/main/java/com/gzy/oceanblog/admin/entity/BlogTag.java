package com.gzy.oceanblog.admin.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog_tag")
public class BlogTag {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToMany(mappedBy = "blogTags")
    private List<Blog> blogs = new ArrayList<>();

    public BlogTag() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "BlogTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
