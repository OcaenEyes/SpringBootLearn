package com.gzy.jpademo.repository;

import com.gzy.jpademo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserId(Long userId);
    User findByUserName(String userName);
    User save(User user);
}
