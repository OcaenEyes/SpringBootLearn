package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogTag;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogTagService {
    void saveBlogTag(BlogTag blogTag) throws NotFoundException;

    BlogTag updateBlogTag(long id,BlogTag blogTag) throws NotFoundException;

    BlogTag getBlogTag(long id);
    BlogTag getBlogTagByNaem(String name);

    void deleteBlogTag(long id);

    Page<BlogTag> listBlogTag(Pageable pageable);

    List<BlogTag> listBlogTag();
}
