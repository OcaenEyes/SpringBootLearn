package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogTag;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogTagService {
    BlogTag saveBlogTag(BlogTag blogTag);

    BlogTag updateBlogTag(long id,BlogTag blogTag) throws NotFoundException;

    BlogTag getBlogTag(long id);

    void deleteBlogTag(long id);

    Page<BlogTag> listBlogTag(Pageable pageable);

    List<BlogTag> listBlogTag();
}
