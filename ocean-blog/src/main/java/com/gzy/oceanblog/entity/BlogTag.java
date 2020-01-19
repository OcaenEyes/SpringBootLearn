package com.gzy.oceanblog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog_tag")
public class BlogTag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "blogTags")
    private List<Blog> blogs = new ArrayList<>();

    public BlogTag() {
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

    @Override
    public String toString() {
        return "BlogTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
