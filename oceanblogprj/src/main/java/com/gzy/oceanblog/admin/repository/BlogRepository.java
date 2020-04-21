package com.gzy.oceanblog.admin.repository;

import com.gzy.oceanblog.admin.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    @Query(value = "SELECT * FROM blog ORDER BY create_time DESC LIMIT 6" ,nativeQuery = true)
    List<Blog> queryBlogsNew();
}
