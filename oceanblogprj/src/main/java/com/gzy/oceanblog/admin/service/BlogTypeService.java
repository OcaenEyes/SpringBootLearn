package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogType;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogTypeService {
    BlogType saveBlogType(BlogType blogType);

    BlogType updateBlogType(long id,BlogType blogType) throws NotFoundException;

    BlogType getBlogType(long id);

    void deleteBlogType(long id);

    Page<BlogType> listBlogType(Pageable pageable);

}
