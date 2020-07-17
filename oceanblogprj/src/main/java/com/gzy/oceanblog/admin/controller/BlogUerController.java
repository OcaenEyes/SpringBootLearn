package com.gzy.oceanblog.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.gzy.oceanblog.admin.entity.BlogUser;
import com.gzy.oceanblog.admin.service.BlogUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BlogUerController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BlogUserService blogUserService;

    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody BlogUser blogUser) {
        logger.info(String.valueOf(blogUser));
        Map map = new HashMap<>();
        try {
            BlogUser blogUser1 = blogUserService.getBlogUserByUserName(blogUser.getUsername());
            if (blogUser1 != null) {
                if (blogUser.getPassword().equals(blogUser1.getPassword())) {

                    map.put("code", "200");
                    map.put("message", "登录成功");

                    return map;
                } else {
                    map.put("code", "102");
                    map.put("message", "用户名密码错误");
                    return map;
                }
            } else {
                map.put("code", "103");
                map.put("message", "用户名密码不存在");
                return map;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(@RequestBody BlogUser blogUser) {
        Object o = blogUserService.saveBlogUser(blogUser);
        return o;
    }
}
