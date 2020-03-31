package com.gzy.oceanblog.admin.controller;

import com.gzy.oceanblog.admin.entity.Blog;
import com.gzy.oceanblog.admin.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import javassist.NotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    @ResponseBody
    public Page<Blog> page(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable){
        return blogService.listBlog(pageable);
    }

    @GetMapping("/getBlog")
    @ResponseBody
    public Blog getBlog(@RequestParam long id){
        return blogService.getBlog(id);
    }

    @PostMapping("/saveBlog")
    @ResponseBody
    public void saveBlog(Blog blog){
        blogService.saveBlog(blog);
    }

    @PostMapping("/updateBlog")
    @ResponseBody
    public void updateBlog(@RequestParam long id, Blog blog) throws NotFoundException {
        blogService.updateBlog(id,blog);
    }

    @GetMapping("/deleteBlog")
    @ResponseBody
    public void deleteBlog(@RequestParam long id){
        blogService.deleteBlog(id);
    }

}
