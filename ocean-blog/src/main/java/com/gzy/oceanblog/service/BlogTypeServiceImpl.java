package com.gzy.oceanblog.service;

import com.gzy.oceanblog.entity.BlogType;
import com.gzy.oceanblog.repository.BlogTypeRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogTypeServiceImpl implements BlogTypeService {

    @Autowired
    private BlogTypeRepository blogTypeRepository;

    @Override
    public BlogType saveBlogType(BlogType blogType) {
        return blogTypeRepository.save(blogType);
    }

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

    @Override
    public BlogType getBlogType(Long id) {
        return blogTypeRepository.getOne(id);
    }

    @Override
    public BlogType getBlogTypeByName(String name) {
        return blogTypeRepository.getTypeByName(name);
    }

    @Override
    public void deleteBlogType(Long id) {
        blogTypeRepository.deleteById(id);
    }

    @Override
    public Page<BlogType> listBlogType(Pageable pageable) {
        return blogTypeRepository.findAll(pageable);
    }

    @Override
    public List<BlogType> listBlogType() {
        return blogTypeRepository.findAll();
    }

}
