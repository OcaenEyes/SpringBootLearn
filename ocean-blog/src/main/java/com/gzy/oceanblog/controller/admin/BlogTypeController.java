package com.gzy.oceanblog.controller.admin;

import com.gzy.oceanblog.entity.Blog;
import com.gzy.oceanblog.entity.BlogTag;
import com.gzy.oceanblog.entity.BlogType;
import com.gzy.oceanblog.service.BlogTypeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BlogTypeController {
    @Autowired
    private BlogTypeService blogTypeService;

    @GetMapping("/admin/types")
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {
        model.addAttribute("page",blogTypeService.listBlogType(pageable));
        return "admin-types";
    }

    @PostMapping("/admin/types")
    public String types(BlogType blogType, RedirectAttributes redirectAttributes){
        BlogType blogType1 = blogTypeService.getBlogTypeByName(blogType.getName());

        if (blogType1 != null){
            redirectAttributes.addFlashAttribute("message","错误,已存在同类目录");
        }else {
            blogTypeService.saveBlogType(blogType);
            redirectAttributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/admin/types/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        blogTypeService.deleteBlogType(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

    @PostMapping("admin/types/update/{id}")
    public String updateTypes(BlogType blogType,RedirectAttributes redirectAttributes) throws NotFoundException {
        BlogType blogType1 = blogTypeService.getBlogTypeByName(blogType.getName());

        if (blogType1 != null){
            redirectAttributes.addFlashAttribute("message","错误,已存在同类目录");
        }else {
            blogTypeService.updateBlogType(blogType.getId(),blogType);
            redirectAttributes.addFlashAttribute("message","更新成功");
        }

        return "redirect:/admin/types";
    }
}
