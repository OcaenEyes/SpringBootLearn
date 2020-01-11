package com.gzy.firstdemo.service;

import com.gzy.firstdemo.entity.User;

import java.util.List;

public interface UserService {
    int save(User user);
    int updateUserName(User user);
    int updateUserRoomId(User user);
    int delete(User user);
    List<User> findAll();
    User findById(Long userId);
}
