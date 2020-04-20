package com.gzy.oceanblog.admin.controller;

import com.gzy.oceanblog.admin.entity.BlogTag;
import com.gzy.oceanblog.admin.service.BlogTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/blogTags")
    @ApiImplicitParam(name = "page")
    @ResponseBody
    public Page<BlogTag> page(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return blogTagService.listBlogTag(pageable);
    }

    @GetMapping("/getBlogTag")
    @ResponseBody
    public BlogTag getBlogTag(@RequestParam long id) {
        return blogTagService.getBlogTag(id);
    }

    @GetMapping("/deleteBlogTag")
    @ResponseBody
    public void deleteBlogTag(@RequestParam long id) {
        blogTagService.deleteBlogTag(id);
    }

    @PostMapping("/saveBlogTag")
    @ResponseBody
    public Object saveBlogTag(@RequestBody BlogTag blogTag) {
        try {
            Object o = blogTagService.saveBlogTag(blogTag);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/updateBlogTag")
    @ResponseBody
    public void updateBlogTag(@RequestParam long id, BlogTag blogTag) throws NotFoundException {
        blogTagService.updateBlogTag(id, blogTag);
    }

    @GetMapping("/tag")
    @ResponseBody
    public List<BlogTag> blogTags() {
        return blogTagService.listBlogTag();
    }
}
