package com.gzy.oceanblog.admin.service;

import com.gzy.oceanblog.admin.entity.BlogUser;
import com.gzy.oceanblog.admin.repository.BlogUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class BlogUserServiceImpl implements BlogUserService {

    @Autowired
    private BlogUserRepository blogUserRepository;

    @Transactional
    @Override
    public BlogUser getBlogUserByUserName(String name) {
        return blogUserRepository.getBlogUserByUserName(name);
    }

    @Override
    public Object saveBlogUser(BlogUser blogUser) {
        Map map = new HashMap();
        BlogUser blogUser1 = blogUserRepository.getBlogUserByUserName(blogUser.getUsername());
        if (blogUser1 == null) {

            blogUserRepository.save(blogUser);
            map.put("code", "200");
            map.put("message", "注册成功");
            return map;
        } else {
            map.put("code", "100");
            map.put("message", "注册失败");
            return map;
        }
    }
}
