package com.gzy.oceanblog.service;

import com.gzy.oceanblog.entity.Blog;
import com.gzy.oceanblog.entity.BlogUser;
import com.gzy.oceanblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public BlogUser checkUser(String username, String password) {
        BlogUser blogUser = userRepository.findByUsernameAndPassword(username,password);
        return blogUser;
    }
}
