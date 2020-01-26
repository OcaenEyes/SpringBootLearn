package com.gzy.oceanblog.controller.admin;

import com.gzy.oceanblog.entity.Blog;
import com.gzy.oceanblog.entity.BlogQuery;
import com.gzy.oceanblog.entity.BlogType;
import com.gzy.oceanblog.entity.BlogUser;
import com.gzy.oceanblog.service.BlogService;
import com.gzy.oceanblog.service.BlogTagService;
import com.gzy.oceanblog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/admin/blogs")
    public String blogs(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {
        model.addAttribute("page",blogService.listBlog(pageable));
        System.out.println(blogService.listBlog(pageable));
        return "admin-blogs";
    }

    @PostMapping("/admin/blogs")
    public String blogs(Blog blog, RedirectAttributes redirectAttributes, HttpSession httpSession){
        blog.setBlogUser((BlogUser) httpSession.getAttribute("user"));
//        List type = new ArrayList();
//        type.add("测试以");
//        blog.setBlogTypes(type);
        System.out.println(blog);
        Blog blog1 = blogService.saveBlog(blog);

        if (blog1 == null){
            redirectAttributes.addFlashAttribute("message","操作失败");
        }else {
            redirectAttributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }
}
