package com.gzy.oceanblog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog_type")
public class BlogType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "blogTypes")
    private List<Blog> blogs = new ArrayList<>();

    public BlogType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "BlogType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
