package com.gzy.firstdemo.repository;

import com.gzy.firstdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
