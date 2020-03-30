package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogType;
import com.gzy.oceanblog.admin.repository.BlogTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {
    @Autowired
    private BlogTypeRepository blogTypeRepository;

    @Override
    public BlogType saveBlogType(BlogType blogType) {
        return blogTypeRepository.save(blogType);
    }

    @Override
    public BlogType updateBlogType(BlogType blogType) {
        return blogTypeRepository.save(blogType);
    }

    @Override
    public BlogType getBlogType(long id) {
        return blogTypeRepository.getOne(id);
    }

    @Override
    public void deleteBlogType(long id) {
        blogTypeRepository.deleteById(id);
    }

    @Override
    public Page<BlogType> listBlogType(Pageable pageable) {
        return blogTypeRepository.findAll(pageable);
    }
}
