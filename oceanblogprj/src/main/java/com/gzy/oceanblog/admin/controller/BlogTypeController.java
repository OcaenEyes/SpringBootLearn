package com.gzy.oceanblog.admin.controller;

import com.gzy.oceanblog.admin.entity.BlogType;
import com.gzy.oceanblog.admin.service.BlogTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api()
public class BlogTypeController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogTypeService blogTypeService;

    @ApiOperation(value = "分页查询文章类型")
    @ApiImplicitParam(name = "page")
    @GetMapping("/blogTypes")
    @ResponseBody
    public Page<BlogType> page(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return blogTypeService.listBlogType(pageable);
    }

    @ApiOperation(value = "根据id查询文章类型")
    @ApiImplicitParam(name = "id")
    @GetMapping("/getBlogType")
    @ResponseBody
    public BlogType getBlogType(@RequestParam Long id) {
        logger.info(String.valueOf(id));
        return blogTypeService.getBlogType(id);
    }

    @ApiOperation(value = "保存文章类型")
    @PostMapping("/saveBlogType")
    @ResponseBody
    public Object saveBlogType(@RequestBody BlogType blogType) throws NotFoundException {
        logger.info("入参信息",String.valueOf(blogType));
        try{
            Object o = blogTypeService.saveBlogType(blogType);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "更新文章类型")
    @PostMapping("/updateBlogType")
    @ResponseBody
    public void updateBlogType(@RequestParam Long id, BlogType blogType) throws NotFoundException {
        blogTypeService.updateBlogType(id,blogType);
    }

    @ApiOperation(value = "删除文章类型")
    @ApiImplicitParam(name = "id")
    @GetMapping("/deleteBlogType")
    @ResponseBody
    public void deleteBlogType(@RequestParam Long id) {
        blogTypeService.deleteBlogType(id);
    }


    @GetMapping("/type")
    @ResponseBody
    public List<BlogType> blogTypes(){
        return blogTypeService.lsitBlogType();
    }

}
