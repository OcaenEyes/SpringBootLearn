package com.gzy.oceanblog.controller.admin;

import com.gzy.oceanblog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
