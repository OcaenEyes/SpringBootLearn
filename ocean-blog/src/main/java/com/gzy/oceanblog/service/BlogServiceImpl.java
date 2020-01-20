package com.gzy.oceanblog.service;

import com.gzy.oceanblog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog saveBlog(Blog blog) {
        return null;
    }

    @Override
    public Blog updateBlog(Blog blog) {
        return null;
    }

    @Override
    public Blog getBlog(Long id) {
        return null;
    }

    @Override
    public Blog getAndConvert(Long id) {
        return null;
    }

    @Override
    public void deleteBlog(Long id, Blog blog) {

    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return null;
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        return null;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        return null;
    }

    @Override
    public Map<String, List<Blog>> archiveBlogByYear(Integer year) {
        return null;
    }
}
