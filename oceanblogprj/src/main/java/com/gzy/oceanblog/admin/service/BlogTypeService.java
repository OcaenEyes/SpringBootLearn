package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogType;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogTypeService {
    Object saveBlogType(BlogType blogType);

    BlogType updateBlogType(long id,BlogType blogType) throws NotFoundException;

    BlogType getBlogType(long id);

    BlogType getBlogTypeByName(String name);

    void deleteBlogType(long id);

    Page<BlogType> listBlogType(Pageable pageable);

    List<BlogType> lsitBlogType();

}
