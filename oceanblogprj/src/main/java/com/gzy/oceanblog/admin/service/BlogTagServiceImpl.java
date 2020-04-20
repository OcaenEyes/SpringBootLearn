package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogTag;
import com.gzy.oceanblog.admin.repository.BlogTagRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogTagServiceImpl implements BlogTagService {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BlogTagRepository blogTagRepository;

    @Transactional
    @Override
    public void saveBlogTag(BlogTag blogTag) {
        String blogTagName = blogTag.getName();
        BlogTag blogTag1 = blogTagRepository.getBlogTagByName(blogTagName);
        if (blogTag1 != null){
            logger.info("已存该标签");
        }else {
            blogTagRepository.save(blogTag);
        }
    }

    @Transactional
    @Override
    public BlogTag updateBlogTag(long id,BlogTag blogTag) throws NotFoundException {
        BlogTag blogTag1 = blogTagRepository.findById(id).get();
        if (blogTag1 == null){
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(blogTag,blogTag1);
        return blogTagRepository.save(blogTag1);
    }

    @Transactional
    @Override
    public BlogTag getBlogTag(long id) {
        return blogTagRepository.findById(id).get();
    }

    @Override
    public BlogTag getBlogTagByNaem(String name) {
        return blogTagRepository.getBlogTagByName(name);
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

    @Override
    public List<BlogTag> listBlogTag() {
        return blogTagRepository.findAll();
    }
}
