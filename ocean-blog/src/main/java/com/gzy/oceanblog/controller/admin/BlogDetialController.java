package com.gzy.oceanblog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gzy.oceanblog.entity.Blog;
import com.gzy.oceanblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BlogDetialController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{id}")
    public String detial(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlog(id);
        model.addAttribute("cblog",blog);
        return "blog";
    }
}
