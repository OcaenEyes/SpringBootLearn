package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogType;
import com.gzy.oceanblog.admin.repository.BlogTypeRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {
    @Autowired
    private BlogTypeRepository blogTypeRepository;

    @Transactional
    @Override
    public BlogType saveBlogType(BlogType blogType) {
        return blogTypeRepository.save(blogType);
    }

    @Transactional
    @Override
    public BlogType updateBlogType(long id,BlogType blogType) throws NotFoundException {
        BlogType blogType1 = blogTypeRepository.findById(id).get();
        if (blogType1 == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(blogType,blogType1);
        return blogTypeRepository.save(blogType1);
    }

    @Transactional
    @Override
    public BlogType getBlogType(long id) {
        return blogTypeRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteBlogType(long id) {
        blogTypeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<BlogType> listBlogType(Pageable pageable) {
        return blogTypeRepository.findAll(pageable);
    }
}
