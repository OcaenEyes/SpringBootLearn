package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogTag;
import com.gzy.oceanblog.admin.entity.BlogType;
import com.gzy.oceanblog.admin.repository.BlogTagRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagRepository blogTagRepository;

    @Transactional
    @Override
    public BlogTag saveBlogTag(BlogTag blogTag) {
        return blogTagRepository.save(blogTag);
    }

    @Transactional
    @Override
    public BlogTag updateBlogTag(long id,BlogTag blogTag) throws NotFoundException {
        BlogTag blogTag1 = blogTagRepository.getOne(id);
        if (blogTag1 == null){
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(blogTag,blogTag1);
        return blogTagRepository.save(blogTag1);
    }

    @Transactional
    @Override
    public BlogTag getBlogTag(long id) {
        return blogTagRepository.getOne(id);
    }

    @Transactional
    @Override
    public void deleteBlogTag(long id) {
        blogTagRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<BlogTag> listBlogTag(Pageable pageable) {
        return blogTagRepository.findAll(pageable);
    }
}
