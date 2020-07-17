package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogUser;

public interface BlogUserService {
    BlogUser getBlogUserByUserName(String name);

    Object saveBlogUser(BlogUser blogUser);
}
