package com.gzy.oceanblog.controller.admin;

import com.gzy.oceanblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = {"/","/index"})
    public String index(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("blogs",blogService.listBlog(pageable));
        return "index";
    }
}
