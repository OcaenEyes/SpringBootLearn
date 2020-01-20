package com.gzy.oceanblog.service;

import com.gzy.oceanblog.entity.BlogType;
import com.gzy.oceanblog.repository.BlogTypeRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public BlogType updateBlogType(Long id, BlogType blogType) throws NotFoundException {
        BlogType blogType1 = blogTypeRepository.getOne(id);
        if (blogType1 ==null){
            throw new NotFoundException("不存在该类型");
        }
        // 将传入值副给 库内的值
        BeanUtils.copyProperties(blogType,blogType1);
        return blogTypeRepository.save(blogType1);
    }

    @Transactional
    @Override
    public BlogType getBlogType(Long id) {
        return blogTypeRepository.getOne(id);
    }

    @Transactional
    @Override
    public BlogType getBlogTypeByName(String name) {
        return blogTypeRepository.getTypeByName(name);
    }

    @Transactional
    @Override
    public void deleteBlogType(Long id) {
        blogTypeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<BlogType> listBlogType(Pageable pageable) {
        return blogTypeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<BlogType> listBlogType() {
        return blogTypeRepository.findAll();
    }

}
