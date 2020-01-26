package com.gzy.oceanblog.service;

import com.gzy.oceanblog.entity.Blog;
import com.gzy.oceanblog.entity.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog saveBlog(Blog blog);

    Blog updateBlog(Blog blog);

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    void deleteBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String ,List<Blog>> archiveBlog();

    Map<String ,List<Blog>> archiveBlogByYear(Integer year);
}
