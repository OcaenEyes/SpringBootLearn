package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogTag;
import com.gzy.oceanblog.admin.repository.BlogTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagRepository blogTagRepository;

    @Override
    public BlogTag saveBlogTag(BlogTag blogTag) {
        return blogTagRepository.save(blogTag);
    }

    @Override
    public BlogTag updateBlogTag(BlogTag blogTag) {
        return blogTagRepository.save(blogTag);
    }

    @Override
    public BlogTag getBlogTag(long id) {
        return blogTagRepository.getOne(id);
    }

    @Override
    public void deleteBlogTag(long id) {
        blogTagRepository.deleteById(id);
    }

    @Override
    public Page<BlogTag> listBlogTag(Pageable pageable) {
        return blogTagRepository.findAll(pageable);
    }
}
