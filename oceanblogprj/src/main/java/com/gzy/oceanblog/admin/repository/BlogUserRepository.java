package com.gzy.oceanblog.admin.repository;

import com.gzy.oceanblog.admin.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {

    @Query("select buser from BlogUser buser where buser.username =?1")
    BlogUser getBlogUserByUserName(String name);
}
