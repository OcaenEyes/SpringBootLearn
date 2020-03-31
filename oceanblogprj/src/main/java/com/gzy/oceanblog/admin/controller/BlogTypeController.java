package com.gzy.oceanblog.admin.controller;

import com.gzy.oceanblog.admin.entity.BlogType;
import com.gzy.oceanblog.admin.service.BlogTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api()
public class BlogTypeController {

    @Autowired
    private BlogTypeService blogTypeService;

    @ApiOperation(value = "分页查询文章类型")
    @GetMapping("/blogTtpes")
    @ResponseBody
    public Page<BlogType> page(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return blogTypeService.listBlogType(pageable);
    }

    @ApiOperation(value = "根据id查询文章类型")
    @ApiImplicitParam(name = "id")
    @GetMapping("/getBlogType")
    @ResponseBody
    public BlogType getBlogType(@RequestParam long id) {
        return blogTypeService.getBlogType(id);
    }

    @ApiOperation(value = "保存文章类型")
    @PostMapping("/saveBlogType")
    @ResponseBody
    public void saveBlogType( BlogType blogType) {
        blogTypeService.saveBlogType(blogType);
    }

    @ApiOperation(value = "更新文章类型")
    @PostMapping("/updateBlogType")
    @ResponseBody
    public void updateBlogType(@RequestParam long id, BlogType blogType) throws NotFoundException {
        blogTypeService.updateBlogType(id,blogType);
    }

    @ApiOperation(value = "删除文章类型")
    @ApiImplicitParam(name = "id")
    @GetMapping("/deleteBlogType")
    @ResponseBody
    public void deleteBlogType(@RequestParam long id) {
        blogTypeService.deleteBlogType(id);
    }


}
