package com.gzy.oceanblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageViewConroller {
    @RequestMapping("/index")
    private String index(){
        return "index";
    }

    @RequestMapping("/tag")
    private String tag(){
        return "tag";
    }

    @RequestMapping("/category")
    private String category(){
        return "category";
    }

    @RequestMapping("/about")
    private String about(){
        return "about";
    }

    @RequestMapping("/admin")
    private String admin(){
        return "admin";
    }

    @RequestMapping("/edit")
    private String edit(){
        return "edit";
    }

}
