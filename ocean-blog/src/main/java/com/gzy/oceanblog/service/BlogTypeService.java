package com.gzy.oceanblog.service;

import com.gzy.oceanblog.entity.BlogType;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogTypeService {

    BlogType saveBlogType(BlogType blogType);

    BlogType updateBlogType(Long id,BlogType blogType) throws NotFoundException;

    BlogType getBlogType(Long id);

    BlogType getBlogTypeByName(String name);

    void deleteBlogType(Long id);

    //分页查询
    Page<BlogType> listBlogType(Pageable pageable);

    List<BlogType> listBlogType();

}
