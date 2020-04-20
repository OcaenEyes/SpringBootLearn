package com.gzy.oceanblog.admin.controller;

import com.gzy.oceanblog.admin.entity.Blog;
import com.gzy.oceanblog.admin.entity.BlogTag;
import com.gzy.oceanblog.admin.entity.BlogType;
import com.gzy.oceanblog.admin.service.BlogService;
import com.gzy.oceanblog.admin.service.BlogTagService;
import com.gzy.oceanblog.admin.service.BlogTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Api
public class BlogController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/blogs")
    @ApiImplicitParam(name = "page")
    @ResponseBody
    public Page<Blog> page(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return blogService.listBlog(pageable);
    }

    @GetMapping("/getBlog")
    @ResponseBody
    public Blog getBlog(@RequestParam Long id) {
        return blogService.getBlog(id);
    }

    @PostMapping("/saveBlog")
    @ResponseBody
//    public void saveBlog( @RequestBody Blog blog){
//        blogService.saveBlog(blog);
//    }
    public Object saveBlog(@RequestBody Map map) throws NotFoundException {
        Map map1 = new HashMap();
        try{
            logger.info(String.valueOf(map));
            Blog blog = new Blog();
            blog.setTitle(map.get("title").toString());
            blog.setContent(map.get("content").toString());
            blog.setIntro(map.get("intro").toString());
            blog.setThumbPic(map.get("thumbPic").toString());
//        ArrayList<Blog> blogs = new ArrayList<>();
//        blogs.add(blog);
//        Integer i = (Integer) map.get("recommend");
//        if (i == 1) {
//            blog.setRecommend(Boolean.TRUE);
//        } else {
//            blog.setRecommend(Boolean.FALSE);
//        }
            blog.setRecommend((Boolean) map.get("recommend"));
            ArrayList<BlogType> blogTypes = new ArrayList<>();
            ArrayList typelist = (ArrayList) map.get("blogTypes");
            for (Object type : typelist
            ) {
                logger.info((String) type);
                BlogType blogType = new BlogType();
                blogType.setName((String) type);
                blogTypeService.saveBlogType(blogType);
                BlogType blogType1 = blogTypeService.getBlogTypeByName((String) type);
                blogTypes.add(blogType1);
            }
            ArrayList<BlogTag> blogTags = new ArrayList<>();
            ArrayList taglist = (ArrayList) map.get("blogTags");
            for (Object tag : taglist
            ) {
                logger.info((String) tag);
                BlogTag blogTag = new BlogTag();
                blogTag.setName((String) tag);
                blogTagService.saveBlogTag(blogTag);
                BlogTag blogTag1 = blogTagService.getBlogTagByNaem((String) tag);
                blogTags.add(blogTag1);
            }
            blog.setBlogTypes(blogTypes);
            blog.setBlogTags(blogTags);
            blogService.saveBlog(blog);
            map1.put("code","200");
            map1.put("messsage","成功");
            return map1;

        } catch (Exception e) {
            e.printStackTrace();
            map1.put("code","100");
            map1.put("messsage","失败");
            return map1;
        }
    }

    @PostMapping("/updateBlog")
    @ResponseBody
    public void updateBlog(@RequestBody Map map) throws NotFoundException, ParseException {
        logger.info(String.valueOf(map));
        Blog blog = new Blog();
        long id = Long.parseLong(map.get("id").toString());
        blog.setId(id);
        blog.setTitle(map.get("title").toString());
        blog.setContent(map.get("content").toString());
        blog.setIntro(map.get("intro").toString());
        blog.setThumbPic(map.get("thumbPic").toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        blog.setCreateTime(sdf.parse((String) map.get("createTime")));
        Date updateTime = new Date();
        blog.setUpdateTime(updateTime);
        blog.setRecommend((Boolean) map.get("recommend"));
        int views = Integer.parseInt(map.get("views").toString());
        blog.setViews(views);
        ArrayList<BlogType> blogTypes = new ArrayList<>();
        ArrayList typelist = (ArrayList) map.get("blogTypes");
        for (Object type : typelist
        ) {
            logger.info((String) type);
            BlogType blogType = new BlogType();
            blogType.setName((String) type);
            blogTypeService.saveBlogType(blogType);
            BlogType blogType1 = blogTypeService.getBlogTypeByName((String) type);
            blogTypes.add(blogType1);
        }
        ArrayList<BlogTag> blogTags = new ArrayList<>();
        ArrayList taglist = (ArrayList) map.get("blogTags");
        for (Object tag : taglist
        ) {
            logger.info((String) tag);
            BlogTag blogTag = new BlogTag();
            blogTag.setName((String) tag);
            blogTagService.saveBlogTag(blogTag);
            BlogTag blogTag1 = blogTagService.getBlogTagByNaem((String) tag);
            blogTags.add(blogTag1);

        }
        blog.setBlogTypes(blogTypes);
        blog.setBlogTags(blogTags);
        blogService.updateBlog(id, blog);
    }

    @GetMapping("/deleteBlog")
    @ResponseBody
    public void deleteBlog(@RequestParam Long id) {
        blogService.deleteBlog(id);
    }

}
