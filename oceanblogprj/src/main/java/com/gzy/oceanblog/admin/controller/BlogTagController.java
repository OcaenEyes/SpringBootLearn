package com.gzy.oceanblog.admin.controller;

import com.gzy.oceanblog.admin.entity.BlogTag;
import com.gzy.oceanblog.admin.service.BlogTagService;
import io.swagger.annotations.Api;
import javassist.NotFoundException;
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
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/blogTags")
    @ResponseBody
    public Page<BlogTag> page(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return blogTagService.listBlogTag(pageable);
    }

    @GetMapping("/getBlogTag")
    @ResponseBody
    public BlogTag getBlogTag(@RequestParam long id){
        return blogTagService.getBlogTag(id);
    }

    @GetMapping("/deleteBlogTag")
    @ResponseBody
    public void  deleteBlogTag(@RequestParam long id){
        blogTagService.deleteBlogTag(id);
    }

    @PostMapping("/saveBlogTag")
    @ResponseBody
    public void  saveBlogTag( BlogTag blogTag){
        blogTagService.saveBlogTag(blogTag);
    }

    @PostMapping("/updateBlogTag")
    @ResponseBody
    public void  updateBlogTag(@RequestParam long id, BlogTag blogTag) throws NotFoundException {
        blogTagService.updateBlogTag(id,blogTag);
    }
}
