package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.Blog;
import com.gzy.oceanblog.admin.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
            blog.setViews(blogRepository.getOne(blog.getId()).getViews());
            blog.setCreateTime(blogRepository.getOne(blog.getId()).getCreateTime());
        }
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public void deleteBlog(long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(long tagId, Pageable pageable) {
        return null;
    }

    @Override
    public List<Blog> listRecommendBlogTop(int size) {
        return null;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        return null;
    }

    @Override
    public Map<String, List<Blog>> archiveBlogByYear(int year) {
        return null;
    }
}
