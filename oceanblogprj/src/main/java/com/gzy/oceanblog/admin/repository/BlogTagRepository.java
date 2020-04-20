package com.gzy.oceanblog.admin.repository;

import com.gzy.oceanblog.admin.entity.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogTagRepository extends JpaRepository<BlogTag,Long> {
    @Query("SELECT btag FROM BlogTag btag WHERE btag.name =?1")
    BlogTag getBlogTagByName(String name);

}
