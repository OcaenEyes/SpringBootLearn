package com.gzy.oceanblog.admin.repository;

import com.gzy.oceanblog.admin.entity.BlogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogTypeRepository extends JpaRepository<BlogType,Long> {
    @Query("SELECT btype FROM BlogType btype WHERE btype.name=?1")
    BlogType getBlogTypeByName(String name);
}
