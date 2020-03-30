package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogTagService {
    BlogTag saveBlogTag(BlogTag blogTag);

    BlogTag updateBlogTag(BlogTag blogTag);

    BlogTag getBlogTag(long id);

    void deleteBlogTag(long id);

    Page<BlogTag> listBlogTag(Pageable pageable);
}
