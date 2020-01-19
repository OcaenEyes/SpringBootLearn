package com.gzy.oceanblog.repository;

import com.gzy.oceanblog.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<BlogUser,Long> {
    BlogUser findByUsernameAndPassword(String username, String password);
}
