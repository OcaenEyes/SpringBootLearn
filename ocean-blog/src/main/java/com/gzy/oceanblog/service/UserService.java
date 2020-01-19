package com.gzy.oceanblog.service;

import com.gzy.oceanblog.entity.BlogUser;

public interface UserService {
    BlogUser checkUser(String username,String password);
}
