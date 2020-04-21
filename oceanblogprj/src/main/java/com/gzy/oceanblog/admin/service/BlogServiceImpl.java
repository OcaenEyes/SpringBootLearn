package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.Blog;
import com.gzy.oceanblog.admin.repository.BlogRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
            blog.setViews(blogRepository.findById(blog.getId()).get().getViews());
            blog.setCreateTime(blogRepository.findById(blog.getId()).get().getCreateTime());
        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(long id,Blog blog) throws NotFoundException {
        Blog blog1 = blogRepository.findById(id).get();
        if (blog1 == null){
            throw new NotFoundException("不存在该文章");
        }
        BeanUtils.copyProperties(blog,blog1);
        return blogRepository.save(blog1);
    }

    @Transactional
    @Override
    public Blog getBlog(long id) {
        return blogRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteBlog(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(long tagId, Pageable pageable) {
        return null;
    }

    @Transactional
    @Override
    public List<Blog> listRecommendBlogTop(int size) {
        return null;
    }

    @Override
    public List<Blog> listBlogNew() {
        return blogRepository.queryBlogsNew();
    }

    @Transactional
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        return null;
    }

    @Transactional
    @Override
    public Map<String, List<Blog>> archiveBlogByYear(int year) {
        return null;
    }
}
