package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog saveBlog(Blog blog);
    Blog updateBlog(Blog blog);
    Blog getBlog(long id);
    void deleteBlog(long id);
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(long tagId,Pageable pageable);

    List<Blog> listRecommendBlogTop(int size);

    Map<String,List<Blog>> archiveBlog();

    Map<String,List<Blog>> archiveBlogByYear(int year);
}
